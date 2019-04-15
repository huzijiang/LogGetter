package com.suixingpay.hw.report.po;

import lombok.Data;

import java.util.Date;

/**
 * @description: 报表信息 PO
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-11 11:15
 **/
@Data
public class ReportInfoPo {
    /**
     * 企业编号
     */
    private Integer enterpriseId;

    private String enterpriseName;

    private Integer reportId;

    private Integer reportTemplateId;

    private String state;

    private String publishState;

    private Date makeDate;

}
