package service

import common.SequenceHelper
import common.Validator
import groovy.util.logging.Log
import sql.StudentDAO
import vanillax.framework.core.db.Transactional
import vanillax.framework.core.object.Autowired
import vanillax.framework.webmvc.service.ServiceBase
import vanillax.webmvc.example.common.DateUtils

@Log
class student extends ServiceBase{
    @Autowired // scripts 디렉토리내에 정의된 모든 Groovy객체를 참조하는 방식
    Validator validator
    @Autowired
    SequenceHelper sequenceHelper
    @Autowired
    StudentDAO studentDAO

    /*
    *  findOne(), findMany()함수가 정의되어있지 않으면 GET방식에 호출된다.
    *  예) GET /my/url/1213
    */
//    @Transactional
//    def find(data){
//        log.info("find()")
//        log.info("data._path : $data._path")
//        log.info("data._param : $data._param")
//        return studentDAO.selectStudentList([:])
//    }

    /*
    *  ID값이 부여된 경우 GET 방식에 기본호출된다.
    *  예) GET /student/123
    */
    @Transactional
    def findOne(data){
        validator.isProperId(data) // url값이 정상적으로 입력되었는지 검증
        def id = data._path
        log.info("id : $id")
        data.id = id
        return studentDAO.selectStudentById(data)
    }

    /*
    *  ID값이 부여되지않은 경우 GET 방식에 기본호출된다.
    *  예) GET /sudent?studentName=XXXX
    */
    @Transactional
    def findMany(data){
        log.info("data._param : $data._param")
        if(data._param.studentName){
            return studentDAO.selectStudentListByName(data._param)
        }else{
            return studentDAO.selectStudentList([:]) // 사실 groovy는 return이 필요없음
        }
    }

    /**
     * POST 방식에서 기본호출
     */
    @Transactional(autoCommit = false)
    def insert(data){
        log.info("data._input : $data._input")
        if(data._input.createdRows){ // 기존엔 for in 문을 사용해 여러번 DAO를 호출 지금은 다건을 한번에 짚어넣음
            log.info("createdRows")
            def list = data._input.createdRows
            def newList = []
            list.each{ it ->
                it.id = sequenceHelper.nextValue('studentSeq')
                newList << it
                DateUtils.putCurrentDate(it)
                studentDAO.insertStudent(it)
            }
            return newList
        }else if(data._input.updatedRows){
            log.info("updatedRows")
            DateUtils.putCurrentDate(data._input.updatedRows)
            return studentDAO.updateStudentList(data._input.updatedRows)
        }
        return null
    }

    /**
     * PUT 방식에서 기본호출
     */
    @Transactional(autoCommit = false)
    def update(data){
        log.info("data._input : $data._input")
        log.info("data._path : $data._path")
        log.info("data._param : $data._param")

        if(data._param && data._param.action == 'delete'){
            return studentDAO.deleteStudentList(data._input)
        }else{
            DateUtils.putCurrentDate(data._input)
            return studentDAO.updateStudentList(data._input)
        }
        return null
    }

    /**
     * DELETE 방식에서 기본호출
     */
    @Transactional(autoCommit = false)
    def delete(data){
        data.id = data._path
        log.info("data._path : $data._path")
        log.info("data._input : $data._input")
        return studentDAO.deleteStudent(data)
    }
}