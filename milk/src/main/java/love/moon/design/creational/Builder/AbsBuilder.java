package love.moon.design.creational.Builder;

/**
 * @auther dongnan
 * @date 2019/11/19 16:41
 * @describe
 */
public abstract class AbsBuilder {

    //创建产品对象
    protected  Product product=new Product();

    public  abstract void buildPartA();
    public  abstract void buildPartB();
    public  abstract void buildPartC();

    //返回产品对象
    public  Product getProduct() {
        return  product;
    }
}
