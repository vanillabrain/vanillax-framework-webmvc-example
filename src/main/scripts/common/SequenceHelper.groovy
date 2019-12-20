package common

import groovy.util.logging.Log
import sql.common.SequenceDAO
import vanillax.framework.core.db.Transactional
import vanillax.framework.core.object.Autowired
import vanillax.framework.webmvc.exception.BaseException

@Log
class SequenceHelper {
    @Autowired
    SequenceDAO sequenceDAO

//    @Transactional(autoCommit = false) //sqlite에서 복수 connection을 허용하지 않음.
    def nextValue(sequenceName){
        def m = sequenceDAO.selectSeq([sequenceName:sequenceName])
        sequenceDAO.updateSeq([sequenceName:sequenceName, sequenceCurValue:m.sequenceCurValue+1])
        return m.sequenceCurValue + 1
    }

}
