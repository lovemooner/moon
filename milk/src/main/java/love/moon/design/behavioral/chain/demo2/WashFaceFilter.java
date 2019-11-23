package love.moon.design.behavioral.chain.demo2;

/**
 * @auther dongnan
 * @date 2019/11/23 19:45
 * @describe
 */
public class WashFaceFilter extends AbstractPrepareFilter {

    public WashFaceFilter(AbstractPrepareFilter nextPrepareFilter) {
        super(nextPrepareFilter);
    }

    @Override
    public void prepare(PreparationList preparationList) {
        if (preparationList.isWashFace()) {
            System.out.println("洗脸");
        }
    }

}
