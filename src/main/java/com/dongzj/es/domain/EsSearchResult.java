package com.dongzj.es.domain;

import java.util.List;

/**
 * 封装ES查询结果
 * <p>
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/8
 * Time: 11:54
 */
public class EsSearchResult<T> {

    Integer total = null;
    List<T> hits = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getHits() {
        return hits;
    }

    public void setHits(List<T> hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "EsSearchResult{" +
                "total=" + total +
                ", hits=" + hits +
                '}';
    }
}
