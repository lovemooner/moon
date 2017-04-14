package love.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * User: lovemooner
 * Date: 17-3-28
 * Time: 上午10:31
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isAllEmpty(Object... str) {
        if (ArrayUtils.isEmpty(str)) return true;
        for (Object s : str) {
            if (s != null && StringUtils.isNotBlank(s.toString())) {
                return false;
            }
        }
        return true;
    }

    public static String valueOf(Object obj) {
        if (obj == null) return "";
        return String.valueOf(obj);
    }

}
