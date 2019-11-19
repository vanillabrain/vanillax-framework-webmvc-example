package vanillax.webmvc.example.common;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DateUtils {

    public static void putCurrentDateMap(Map<String, Object> map)throws Exception{
        map.put("currentDate", new Timestamp(System.currentTimeMillis()));
    }

    public static void putCurrentDate(Object object)throws Exception{
        if(object == null)
            return;
        if(object instanceof List){
            List list = (List)object;
            for(Object o:list){
                if(o instanceof Map){
                    Map<String, Object> m = (Map<String, Object>)o;
                    putCurrentDateMap(m);
                }
            }
        }else if(object instanceof Map){
            Map<String, Object> m = (Map<String, Object>)object;
            putCurrentDateMap(m);
        }
    }

    public static long diffFromNow(Timestamp timestamp){
        if(timestamp == null)
            return -1;
        return System.currentTimeMillis() - timestamp.getTime();
    }

    public static boolean isValid(Timestamp timestamp, long minutes){
        if(timestamp == null)
            return false;
        return System.currentTimeMillis() - timestamp.getTime() <= minutes * 60 * 1000L;
    }

}
