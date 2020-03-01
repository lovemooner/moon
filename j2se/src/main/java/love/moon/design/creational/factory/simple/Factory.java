package love.moon.design.creational.factory.simple;

import love.moon.design.creational.factory.BusinessA;
import love.moon.design.creational.factory.BusinessB;
import love.moon.design.creational.factory.IBusiness;

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