package love.moon.design.factory.simple;

import love.moon.design.factory.BusinessA;
import love.moon.design.factory.BusinessB;
import love.moon.design.factory.IBusiness;

public class Factory {

    public static IBusiness create(String type) {
        if ("A".equals(type)) {
            return new BusinessA();
        } else if ("B".equals(type)) {
            return new BusinessB();
        } else {
            return null;
        }
    }

}