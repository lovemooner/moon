package love.moon.common;

/**
 * Created by nadong on 2017/4/25.
 */
public class Pager {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private static final int DEFAULT_PAGE_INDEX = 1;

    private static final String INIT_PAGE_FAILED = "初始化分页组件失败！";

    private int currentPage = DEFAULT_PAGE_INDEX;

    private int totalPage;

    private int totalRows;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private int PageRows;

    private int nextPage;

    private int lastPage;

    private int start;

    private int limit;

    private int rowEnd;

    private boolean isFirstPage;

    private boolean isLastPage;

    private boolean hasNextPage;

    private boolean hasPreviousPage;


    public int getLastPage() {
        return lastPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getNextPage() {
        return nextPage;
    }

    /**
     * 当前页总记录数
     */
    public int getPageRows() {
        if (this.getIsLastPage()) {
            // 最后一页时
            return this.totalRows - (this.pageSize * (this.totalPage - 1));
        } else {
            return this.getPageSize();
        }
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        if (currentPage >= this.DEFAULT_PAGE_INDEX && currentPage <= this.getTotalPage() && currentPage != this.currentPage) {
            this.currentPage = currentPage;
        }
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRowEnd() {
        return rowEnd;
    }

    public void setRowEnd(int rowEnd) {
        this.rowEnd = rowEnd;
    }

    public boolean getIsFirstPage() {
        return this.currentPage == this.DEFAULT_PAGE_INDEX;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean getIsLastPage() {
        return this.currentPage == this.totalPage;
    }


    public boolean getHasNextPage() {
        return this.hasNextPage();
    }

    public boolean hasNextPage() {
        return this.totalPage > this.currentPage;
    }

    public boolean getHasPreviousPage() {
        //当前页索引号大于默认的初始页号,这里为1
        return this.currentPage > this.DEFAULT_PAGE_INDEX;
    }

    public void gotoFirstPage() {
        this.gotoPage(this.DEFAULT_PAGE_INDEX);
    }

    public void gotoLastPage() {
        this.gotoPage(this.getTotalPage());

    }

    public void gotoPreviousPage() {
        this.gotoPage(this.getCurrentPage() - 1);

    }



    public void gotoPage(int newPageIndex) {
        if (newPageIndex >= this.DEFAULT_PAGE_INDEX && newPageIndex <= this.getTotalPage()) {
            this.setCurrentPage(newPageIndex);
        }
    }

    public Pager(){

    }






}
