package love.moon.common;

import java.util.List;

/**
 * Created by lovemooner on 2017/4/25.
 */
public abstract class ListResult<T> extends Result{

    protected List<T> results;

    public ListResult() {
    }

    public ListResult(List<T> results, boolean success) {
        super(success);
        this.results = results;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
