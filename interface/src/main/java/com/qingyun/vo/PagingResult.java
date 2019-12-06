package com.qingyun.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/12/5 0005 14:15
 */
@Data
public class PagingResult<T> implements Serializable {

    /**
     * 当前页数
     */
    private int page;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 每页数据记录
     */
    private List<T> rows;
}
