package com.suixingpay.hw.report.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 指标数据信息
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-16 13:32
 **/
@Data
public class TargetDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 报告各个指标对应的发布内容
     */
    private String targetDataPublishContent;

    /**
     * 报告各个指标对应的发布内容的编号
     */
    private Integer enterpriseTargetDataId;
}
