package io.bms.bmswk.model.support;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * page object
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 10:52
 */
public class Pagination implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * total records
     */
    private int totalCount;
    /**
     * record num in one page
     */
    private int pageSize;
    /**
     * total pages
     */
    private int totalPage;
    /**
     * curent page no
     */
    private int currPage;
    /**
     * data for the pagination
     */
    private List<?> list;

    /**
     * oagination
     * @param list        list data
     * @param totalCount  total records
     * @param pageSize    records on one page
     * @param currPage    current page num
     */
    public Pagination(List<?> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
    }

    /**
     * pagination
     */
    public Pagination(IPage<?> page) {
        this.list = page.getRecords();
        this.totalCount = (int)page.getTotal();
        this.pageSize = (int)page.getSize();
        this.currPage = (int)page.getCurrent();
        this.totalPage = (int)page.getPages();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
