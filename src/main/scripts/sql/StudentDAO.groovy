package sql

import vanillax.framework.core.db.orm.*
import vanillax.framework.core.db.script.Velocity

@Repository
interface StudentDAO {
    // 다건 조회
    @Select('''
            SELECT 
                A.id
                , A.studentName
                , A.email
                , A.age
                , A.studentRole
                , A.visitCnt
                , B.codeName as studentRoleName 
                , A.regDate
                , A.modDate
            FROM Student A left outer join CommonCodeDetail B
            ON A.studentRole = B.codeDetail
        ''')
    List selectStudentList(Map x)

    // 단건 조회 BY ID
    @Select('''
            SELECT id
                   , studentName
                   , email
                   , age
                   , studentRole
                   , visitCnt
                   , regDate
            FROM Student
            WHERE id = :id
    ''')
    Map selectStudentById(Map x)

    // 단건 조회 BY studentName
    @Select('''
            SELECT A.id
                , A.studentName 
                , A.email
                , A.age
                , A.studentRole
                , A.visitCnt
                , B.codeName as studentRoleName
                , A.regDate
                , A.modDate
            FROM Student A left outer join CommonCodeDetail B
            ON A.studentRole = B.codeDetail
            WHERE studentName like '%'|| :studentName || '%'
    ''')
    List selectStudentListByName(Map x)

    // 학생정보 입력 [다건] List 안에 있는 데이터들을 하나씩 가져와서 수행, 단건도 수행 가능
    @Insert('''
            INSERT INTO Student(id, studentName, email, age, visitCnt, studentRole, regDate)
            VALUES(
              :id
            , :studentName
            , :email
            , :age
            , :visitCnt
            , :studentRole
            , CURRENT_TIMESTAMP
            )
    ''')
    def insertStudent(Map x)

    // 학생정보 수정 [다건]
    @Update('''
        UPDATE Student  
        SET 
            studentName = :studentName
            , email = :email 
            , age = :age 
            , visitCnt = :visitCnt 
            , studentRole = :studentRole
            , modDate = CURRENT_TIMESTAMP
        WHERE id = :id
    ''')
    List updateStudentList(List list)

    // 학생정보 삭제 [단건]
    @Delete('''
        DELETE FROM Student WHERE id = :id
    ''')
    boolean deleteStudent(Map x)

    // 학생정보 삭제 [다건]
    @Delete('''
        DELETE FROM Student WHERE id = :id
    ''')
    List deleteStudentList(List list)
}