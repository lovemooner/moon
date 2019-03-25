package love.moon.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    @Value("${test.name}")
    public static  String name;

}
