package love.moon.spring.service;

import love.moon.spring.model.HomeIP;

import java.util.List;

public interface IHomeService {

    List<HomeIP> getHomeIps(int start, int limit);

    int countHomeIps();
}
