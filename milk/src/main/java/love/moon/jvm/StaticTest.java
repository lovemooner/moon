package love.moon.jvm;

import java.util.Vector;

/**
 * Author: lovemooner
 * Date: 2018/1/12 14:29
 */
public class StaticTest {
    private static Vector v = new Vector(10);

    public void init() {
        for (int i = 1; i < 100; i++) {
            Object object = new Object();
            v.add(object);
            object = null;
        }
    }
}


