package sql.common

import vanillax.framework.core.db.orm.Insert
import vanillax.framework.core.db.orm.Repository
import vanillax.framework.core.db.orm.Select
import vanillax.framework.core.db.script.Velocity

@Repository
interface FileDAO {

    @Select('''
        SELECT
              id
            , fileName
            , fileExt
            , filePath
            , regUser
            , regDate
            , modUser
            , modDate 
        FROM File
        WHERE id = :id
    ''')
    Map selectFile(Map x)

    @Insert('''
        INSERT INTO File(id, fileName, fileExt, filePath, regUser, regDate, modUser, modDate) 
        VALUES(:id, :fileName, :fileExt, :filePath, :_userId, CURRENT_TIMESTAMP, :_userId, CURRENT_TIMESTAMP)
    ''')
    def insertFile(Map x)


}