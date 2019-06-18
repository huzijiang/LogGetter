package com.suixingpay.hw.platform.domain;

import lombok.Data;

/**
 * @description: 指标仓库
 * @author: xu_pf@suixingpay.com
 * @create: 2019-06-14 12:47
 **/
@Data
public class TargetWarehouse {
    private static final long serialVersionUID = 1L;

    private Integer targetId;

    private String classify;

    private Integer level;

    private String name;

    private String defineDesc;

    private String designFormulas;

    private String unitDesc;

    private String dataSource;

    private String collationCode;

    private String drillOrgLevel;

    /** 是否是时间区间 00 为时间区间 01 为时间段 **/
    private String isTimeInterval;
}
