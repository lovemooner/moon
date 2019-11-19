package love.moon.design.creational.Builder;

/**
 * @auther dongnan
 * @date 2019/11/19 16:42
 * @describe
 */
public class Director {

    private AbsBuilder builder;

    public Director(AbsBuilder builder) {
        this.builder = builder;
    }

    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getProduct();
    }
}
