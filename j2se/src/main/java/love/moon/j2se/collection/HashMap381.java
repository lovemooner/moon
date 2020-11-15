package love.moon.j2se.collection;

import java.util.HashMap;

/**
 * Author: lovemooner
 * Date: 2017/10/16 13:23
 */
public class HashMap381 {

    public static void main(String[] args) {
        HashMap map=new HashMap();
        for(int i=0;i<1000;i++){
            map.put("key"+i,"value"+i);
        }
        map.put("key-testBR","value-testBR");
        System.out.println("main");
    }
}
