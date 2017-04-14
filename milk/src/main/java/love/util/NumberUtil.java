package love.util;

import java.math.BigDecimal;

/**
 * User: lovemooner
 * Date: 17-3-28
 * Time: 上午10:33
 */
public class NumberUtil {

    public static int intValue(String numStr, int defVal) {
        if (StringUtil.isEmpty(numStr)) {
            return defVal;
        }
        try {
            return Integer.parseInt(numStr);
        } catch (NumberFormatException nfe) {
            return defVal;
        }
    }

    public static int intValue(Integer numStr, int defVal) {
        if (numStr == null) {
            return defVal;
        }
        try {
            return intValue(numStr);
        } catch (NumberFormatException nfe) {
            return defVal;
        }
    }

    public static Integer intValue(Object numObj) {
        if (numObj == null || !isNumber(numObj.toString())) {
            return 0;
        }
        return Integer.parseInt(StringUtil.valueOf(numObj));
    }

    public static int intValue(Long num) {
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /**
     * 判断字符串能否转为数值
     *
     * @param value
     * @return
     */
    public static boolean isNumber(String value) {
        if (StringUtil.isEmpty(value)) {
            return false;
        }
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * 保证不丢失精准度
     *
     * @param param1
     * @param param2
     * @return
     */
    public static Double subtract(Double param1, Double param2) {
        return subtract(String.valueOf(param1), String.valueOf(param2));
    }

    public static Double subtract(String param1, String param2) {
        param1 = StringUtil.isEmpty(param1) ? "0" : param1;
        param2 = StringUtil.isEmpty(param2) ? "0" : param2;
        BigDecimal a = new BigDecimal(param1);
        BigDecimal b = new BigDecimal(param2);
        return a.subtract(b).doubleValue();
    }

    public static Double add(Double param1, Double param2) {
        BigDecimal a = new BigDecimal(String.valueOf(param1));
        BigDecimal b = new BigDecimal(String.valueOf(param2));
        return a.add(b).doubleValue();
    }

}
