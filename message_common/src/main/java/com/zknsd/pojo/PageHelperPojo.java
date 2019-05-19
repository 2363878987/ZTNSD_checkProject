package com.zknsd.pojo;

import java.util.List;

public class PageHelperPojo {
    //返回的总条数
    private long total;
    //返回的集合
    private List rows;

    public PageHelperPojo() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
