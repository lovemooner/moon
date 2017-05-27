package love.moon.spring.service;

import love.moon.spring.model.Cart;
import love.moon.spring.service.base.IBaseService;

import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/5/27 15:17
 */
public interface ShoppingCartService  {
    List<Cart> getCarts();

    void initData();

}
