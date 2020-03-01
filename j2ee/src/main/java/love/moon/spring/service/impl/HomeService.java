package love.moon.spring.service.impl;

import love.moon.spring.dao.HomeIpDao;
import love.moon.spring.model.HomeIP;
import love.moon.spring.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomeService implements IHomeService {

    @Autowired
    private HomeIpDao homeIpDao;


    public List<HomeIP> getHomeIps(int start, int limit) {
        return homeIpDao.getHomeIps(start,limit);
    }

    public int countHomeIps() {
        return homeIpDao.countHomeIps();
    }

}
