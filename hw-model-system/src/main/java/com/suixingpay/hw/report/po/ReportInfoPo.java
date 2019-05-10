package com.suixingpay.hw.report.po;

import lombok.Data;

import java.util.Date;

/**
 * @description: 报告信息 PO
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-11 11:15
 **/
@Data
public class ReportInfoPo {
    /**
     * 企业编号
     */
    private Integer enterpriseId;

    /**
     * 报告编号
     */
    private Integer reportId;

    /**
     * 报告名称
     */
    private String reportName;

    /**
     * 报告模版编号
     */
    private Integer reportTemplateId;

    /**
     * 报告应用状态：00-停用 01-启用
     */
    private String state;

    /**
     * 报告发布状态：00-未发布 01-已发布
     */
    private String publishState;

    /**
     * 报告快照生成日期
     */
    private Date makeDate;

    /**
     * 报告快照生成周期
     */
    private String makeCycle;

    /**
     * 报告发布时间
     */
    private Date publishDate;
}
