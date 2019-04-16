package com.arborcommunity.common;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 李品先
 * @Date: 2019/1/25 14:19
 * @Description: ${description}
 */
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<T> records;
    private int total;
    private int size;
    private int pages;
    private int current;

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
