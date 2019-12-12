package sql.common

import vanillax.framework.core.db.orm.*

@Repository
interface SequenceDAO {

    @Select('''
        SELECT sequenceName, sequenceIncrement, sequenceMinValue, sequenceMaxValue, sequenceCurValue, sequenceCycle 
        FROM CommonSequenceData
        WHERE sequenceName = :sequenceName
    ''')
    Map selectSeq(Map x)

    @Update('''
        UPDATE CommonSequenceData
            SET sequenceCurValue = :sequenceCurValue
        WHERE sequenceName = :sequenceName
    ''')
    int updateSeq(Map x)

}