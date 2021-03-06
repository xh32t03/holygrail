package com.avalon.holygrail.ss.view;

import com.avalon.holygrail.ss.norm.Limit;
import com.avalon.holygrail.ss.norm.ResultInfo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 分页数据视图
 */
public class LimitDataView extends PageView {

    /**结果集*/
    private Collection<?> rows = new ArrayList<>();

    public LimitDataView(ResultInfo resultInfo) {
        super(resultInfo);
    }

    public LimitDataView(ResultInfo resultInfo, Collection<?> rows) {
        super(resultInfo);
        this.rows = rows;
    }

    public LimitDataView(int total, int currPage, int pageSize, int totalPage, Collection<?> rows) {
        super(total, currPage, pageSize, totalPage);
        this.rows = rows;
    }

    public LimitDataView(Limit limit, Collection<?> rows) {
        super(limit);
        this.rows = rows;
    }

    public LimitDataView(ResultInfo resultInfo, int total, int currPage, int pageSize, int totalPage, Collection<?> rows) {
        super(resultInfo, total, currPage, pageSize, totalPage);
        this.rows = rows;
    }

    public LimitDataView(ResultInfo resultInfo, Limit limit, Collection<?> rows) {
        super(resultInfo, limit);
        this.rows = rows;
    }

    public LimitDataView(ResultInfo resultInfo, Limit limit) {
        super(resultInfo, limit);
    }


    public Collection<?> getRows() {
        return rows;
    }

    /**
     * 结果集
     */
    public void setRows(Collection<?> rows) {
        this.rows = rows;
    }
}
