package sql

import vanillax.framework.core.db.orm.*
import vanillax.framework.core.db.script.Velocity

@Repository
interface StudentDAO {

    @Select('''
            SELECT 
                A.id, A.studentName, A.email, A.age, A.studentRole, B.codeName as studentRoleName, 
                A.regDate, A.modDate 
            FROM Student A left outer join CommonCodeDetail B
            ON A.studentRole = B.codeDetail
        ''')
    List selectStudentList(Map x)

}