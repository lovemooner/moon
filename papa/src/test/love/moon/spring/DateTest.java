package love.moon.spring;

import love.moon.util.DateUtil;

/**
 * Author: lovemooner
 * Date: 2018/9/2 14:02
 */
public class DateTest {

    public static void main(String[] args) {
      Long date=  DateUtil.getInnerDayTime(-1);
        System.out.println(DateUtil.convertDateLongToString(date,DateUtil.ALL));
    }
}


