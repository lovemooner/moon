package love.moon.common;

import java.util.List;

/**
 * Created by nadong on 2017/4/25.
 */
public class PagingListResult<T> extends ListResult<T> {
    private Pager pager;

    public PagingListResult() {
    }

    public PagingListResult(List<T> results, Pager pager) {
        this.pager = pager;
        this.results = results;
    }

    public PagingListResult(List<T> results, boolean success, Pager pager) {
        super(results, success);
        this.pager = pager;
    }

    public Pager getPager() {
        return pager;
    }

}
