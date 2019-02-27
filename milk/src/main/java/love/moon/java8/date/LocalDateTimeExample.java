package love.moon.java8.date;

import java.time.LocalDateTime;

public class LocalDateTimeExample {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.printf(now.toString());
        LocalDateTime waterStart =LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(),3,0);
        LocalDateTime waterEndTime =LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(),4,0);
    }
}
