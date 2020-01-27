package love.moon.spring.service.impl;

import love.moon.spring.dao.ProductDao;
import love.moon.spring.model.Product;
import love.moon.spring.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Author: lovemooner
 * Date: 2017/6/20 14:53
 */
@Component
public class ProductService implements IProductService {

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
