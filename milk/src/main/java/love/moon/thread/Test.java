package love.moon.thread;

/**
 * Author: lovemooner
 * Date: 2017/11/13 16:39
 */
public class Test {
    public static void main(String[] args) {
        String messageName="ASE_SCIM_ATTRIBUTE_REQUIRED";
        String productCode=messageName.substring(0,messageName.indexOf("_"));
        System.out.println(productCode);
    }
}
