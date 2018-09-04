package love.moon.spring.service;

import love.moon.spring.common.ServiceException;
import love.moon.spring.model.Cart;

import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/6/14 9:42
 */
public interface ICartService {

    void updateCart() throws ServiceException;

    void updateCart2() throws ServiceException;

    List<Cart> getCarts(int start, int limit);

}
