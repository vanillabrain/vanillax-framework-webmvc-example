package vanillax.webmvc.example.common

import spock.lang.Specification

class StringUtilsTest extends Specification {

    def "hashTest"(){
        setup:
        def s = StringUtils

        when:
        def hashed = StringUtils.hash(Constants.HASH_PREFIX, pw)

        then:
        hashed == expedted
        println "-----------------"
        println "$pw hashed : $hashed"

        where:
        pw           | expedted
        'hello123'   | '28F454366F1E3E02D1956DBCE79678D8'
        'gaga123'    | '8845C6DFDF3E99D07DB65455CDBF3D31'
        '1234'    | '0DF5BBE48B33CA75B681E25CE1AC12DF'

    }
}
