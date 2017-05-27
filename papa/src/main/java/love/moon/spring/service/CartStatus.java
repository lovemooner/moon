package love.moon.spring.service;

/**
 * Author: lovemooner
 * Date: 2017/5/27 15:57
 */
public enum CartStatus {
    CREATE,DELETED,INVALID,PAID;

    public static String[] toArray(){
        String[] status={"CREATE","DELETED","INVALID","PAID"};
        return status;
    }
}
