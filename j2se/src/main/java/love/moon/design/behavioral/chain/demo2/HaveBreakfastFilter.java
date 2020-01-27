package love.moon.design.behavioral.chain.demo2;

/**
 * @auther lovemooner
 * @date 2019/11/23 19:50
 * @describe
 */
public class HaveBreakfastFilter extends AbstractPrepareFilter {

    public HaveBreakfastFilter(AbstractPrepareFilter nextPrepareFilter) {
        super(nextPrepareFilter);
    }

    @Override
    public void prepare(PreparationList preparationList) {
        if (preparationList.isHaveBreakfast()) {
            System.out.println("吃早餐");
        }

    }

}