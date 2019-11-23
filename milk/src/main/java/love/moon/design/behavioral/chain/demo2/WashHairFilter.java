package love.moon.design.behavioral.chain.demo2;

/**
 * @auther dongnan
 * @date 2019/11/23 19:49
 * @describe
 */
public class WashHairFilter extends AbstractPrepareFilter {

    public WashHairFilter(AbstractPrepareFilter nextPrepareFilter) {
        super(nextPrepareFilter);
    }

    @Override
    public void prepare(PreparationList preparationList) {
        if (preparationList.isWashHair()) {
            System.out.println("洗头");
        }

    }

}
