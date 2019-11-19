package sql.common

import vanillax.framework.core.db.orm.Delete
import vanillax.framework.core.db.orm.Insert
import vanillax.framework.core.db.orm.Repository
import vanillax.framework.core.db.orm.Select
import vanillax.framework.core.db.orm.Update
import vanillax.framework.core.db.script.Velocity

@Repository
interface UsersDAO {

    @Velocity
    @Select('''
        SELECT
              userId
            , userName
            , passwd
            , email
            , permissionLevel
            , regUser
            , regDate
            , modUser
            , modDate 
        FROM Users 
        WHERE 1=1
        #if($userId) 
            AND userId = :userId 
        #end
    ''')
    List selectUsers(Map x)

    @Select('''
        SELECT
              userId
            , userName
            , passwd
            , email
            , permissionLevel
            , regUser
            , regDate
            , modUser
            , modDate 
        FROM Users
        WHERE userId = :userId
    ''')
    Map selectUser(Map x)

    @Select('''
        SELECT
            A.userId, A.userName, A.passwd, A.email, A.permissionLevel, B.token, B.tokenUpdateDate,
            B.regUser, B.regDate, B.modUser, B.modDate
        FROM Users A, UsersToken B
        WHERE A.userId = B.userId
          AND B.token = coalesce(:token,'AAA')
    ''')
    Map selectUserByToken(Map x)

    @Velocity
    @Update('''
        UPDATE Users
           SET
                #if($token)
                    token = :token,
                #end
                #if($tokenUpdateDate)
                    tokenUpdateDate = :tokenUpdateDate
                #else
                    tokenUpdateDate = :currentDate
                #end
        WHERE userId = :userId
    ''')
    int updateUserToken(Map x)

    @Update('''
        UPDATE UsersToken
           SET
                modUser = :_userId,
                modDate = :currentDate, 
                tokenUpdateDate = :currentDate
        WHERE token = :token
    ''')
    int updateUsersTokenUpdateDateByToken(Map x)

    @Insert('''
        INSERT INTO UsersToken(userId, token, tokenUpdateDate, regUser, regDate, modUser, modDate) 
        VALUES(:userId, :token, :currentDate, :_userId, :currentDate, :_userId, :currentDate)
    ''')
    def insertUsersToken(Map x)

    @Delete('''
        DELETE 
          FROM UsersToken
         WHERE userId = :userId
           AND tokenUpdateDate < :tokenTimeout
    ''')
    boolean deleteUsersTokenOldByUserId(Map x)

    @Update('''
        UPDATE UsersToken
           SET
                modUser = :_userId,
                modDate = :currentDate, 
                tokenUpdateDate = :currentDate
        WHERE token = :token
    ''')
    int updateUsersTokenUpdateAsInvalidate(Map x)

}