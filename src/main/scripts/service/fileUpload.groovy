package service

import common.SequenceHelper
import groovy.util.logging.Log
import sql.common.FileDAO
import vanillax.framework.core.db.Transactional
import vanillax.framework.core.object.Autowired
import vanillax.framework.webmvc.service.ServiceBase

@Log
class fileUpload extends ServiceBase{
    @Autowired
    FileDAO fileDAO
    @Autowired
    SequenceHelper sequenceHelper

    @Transactional(autoCommit = false)
    def insert(data){

        def list = data.uploadFileList;
        def fileList = []
        list.each{ it ->
            it.id = sequenceHelper.nextValue('fileSeq')
            fileDAO.insertFile(it)
            it.url = "/file/fileDownload/$it.id"
            fileList << it
        }
        return fileList
    }

}