package love.moon.common;

import java.util.List;

/**
 * Created by lovemooner on 2017/4/25.
 */
public class AllListResult<T> extends ListResult<T> {
    protected int totalRows;

    public AllListResult() {
    }

    public AllListResult(List<T> results, boolean success, int totalRows) {
        super(results, success);
        this.totalRows = totalRows;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }
}
