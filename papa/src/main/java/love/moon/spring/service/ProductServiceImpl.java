package love.moon.spring.service;

import love.moon.spring.common.ServiceException;
import love.moon.spring.dao.ProductDao;
import love.moon.spring.model.Product;
import love.moon.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Author: lovemooner
 * Date: 2017/6/20 14:53
 */
@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public void updateProduct()  {
        Product product = productDao.getById(5l);
        Random random = new Random();
        String ProductName=product.getProductName();
        product.setProductName(String.valueOf(random.nextInt(100)));
        System.out.println("Old ProductName:"+ProductName+" New ProductName:"+product.getProductName());
        productDao.update(product);
    }


}