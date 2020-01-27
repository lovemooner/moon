package love.moon.spring.model;

import love.moon.spring.model.base.LongIdentifier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: lovemooner
 * Date: 2017/5/27 14:49
 */
@Entity
@Table(name = "ld_cart_item")
public class CartItem extends LongIdentifier {

    private Long cartId;
    private Long productId;
    private Integer itemAmount;
    private Double itemSum;


    @Column(name = "CART_ID",unique = true)
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    @Column(name = "ITEM_AMOUNT")
    public Integer getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Integer itemAmount) {
        this.itemAmount = itemAmount;
    }

    @Column(name = "ITEM_SUM")
    public Double getItemSum() {
        return itemSum;
    }

    public void setItemSum(Double itemSum) {
        this.itemSum = itemSum;
    }

    @Column(name = "product_id")
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}
