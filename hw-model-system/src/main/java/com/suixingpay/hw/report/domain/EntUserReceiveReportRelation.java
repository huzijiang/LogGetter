package com.suixingpay.hw.report.domain;

import lombok.Data;

import java.util.Date;

/**
 * @description: 企业用户邮箱接收 报告信息关系
 * @author: xu_pf@suixingpay.com
 * @create: 2019-05-16 11:03
 **/
@Data
public class EntUserReceiveReportRelation {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer enterpriseId;

    private Integer enterpriseReportTemplateId;

    private Integer enterpriseUserId;

    private String email;

    private Integer creater;

    private Date createDate;

    private String userType;

    private String enterpriseUserName;

}
