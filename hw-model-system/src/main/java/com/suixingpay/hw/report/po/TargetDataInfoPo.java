package com.suixingpay.hw.report.po;

import lombok.Data;

import java.util.Date;

/**
 * @description: 指标数据信息
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-17 17:18
 **/
@Data
public class TargetDataInfoPo {
    /**
     * 报告各个指标对应的发布内容的编号
     */
    private Integer enterpriseTargetDataId;

    private Integer targetModelId;

    private Integer targetModelTemplateId;

    private Integer enterpriseId;

    private Integer enterpriseReportId;

    private Integer reportTemplateId;

    private Integer partContentId;

    private Integer fullContentId;

    private Date makeDate;

    private String publishState;

    private Date publishDate;

    private Integer publisher;

    private Integer belongOrg;

}
