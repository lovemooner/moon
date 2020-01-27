import love.moon.spring.config.MyNamingStrategy;
import org.junit.Test;

/**
 * Author: lovemooner
 * Date: 2017/5/19 13:30
 */
public class NamingStrategyTest {

    @Test
    public void property() {
        MyNamingStrategy propertyName = new MyNamingStrategy();
        String p_nam = propertyName.propertyToColumnName("loginName");
        System.out.println("property_name is:" + p_nam);
    }

    @Test
    public void table() {
        System.currentTimeMillis();
        MyNamingStrategy propertyName = new MyNamingStrategy();
        String p_nam = propertyName.classToTableName("Person");
        System.out.println("column_name is:" + p_nam);

    }
}