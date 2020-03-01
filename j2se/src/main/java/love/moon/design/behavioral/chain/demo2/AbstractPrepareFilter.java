package love.moon.design.behavioral.chain.demo2;

/**
 * @auther lovemooner
 * @date 2019/11/23 19:46
 * @describe
 */
public abstract class AbstractPrepareFilter {

    private AbstractPrepareFilter nextPrepareFilter;

    public AbstractPrepareFilter(AbstractPrepareFilter nextPrepareFilter) {
        this.nextPrepareFilter = nextPrepareFilter;
    }

    public void doFilter(PreparationList preparationList, Study study) {
        prepare(preparationList);

        if (nextPrepareFilter == null) {
            study.study();
        } else {
            nextPrepareFilter.doFilter(preparationList, study);
        }
    }

    public abstract void prepare(PreparationList preparationList);

}
