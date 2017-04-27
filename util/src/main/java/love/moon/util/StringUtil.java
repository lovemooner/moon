package love.moon.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Created by lovemooner on 2017/4/25.
 */
public class StringUtil extends StringUtils {


    public static String valueOf(Object obj){
        if(obj==null) return "";
        return String.valueOf(obj);
    }

    /**
     * String数组有空值
     *
     * @param str
     * @return
     */
    public static boolean hasEmptyVal(String... str) {
        if (ArrayUtils.isEmpty(str)) return true;
        for (String s : str) {
            if (isEmpty(s)) {
                return true;
            }
        }
        return false;
    }
}
