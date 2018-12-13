package com.tt.springbootorder.config.page;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: com.tt.springbootorder.config.page.PageResultBean
 * @Description:
 * @Author: Administrator
 * @CreateDate: 2018/12/6 15:25
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
public class PageResultBean<T> implements Serializable {
    private static final long serialVersionUID = -5596571903936927389L;


    /**
     * 总记录数
     */
    private Long total;

    /**
     * 结果集
     */
    private List<T> rows;

    /**
     * 页数
     */
    private Integer pageNum;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer pages;

    public PageResultBean() {
    }

    public PageResultBean(List<T> rows) {
        init(rows);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    private void init(List<T> rows) {
        if (rows instanceof Page) {
            Page<T> page = (Page<T>) rows;
            this.total = page.getTotal();
            this.rows = page.getResult();
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
        }
    }
}
