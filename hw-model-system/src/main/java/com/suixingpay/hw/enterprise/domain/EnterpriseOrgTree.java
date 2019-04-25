package com.suixingpay.hw.enterprise.domain;

import lombok.Data;

import java.util.Date;

/**
 * @description: 企业组织结构
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-25 10:35
 **/
@Data
public class EnterpriseOrgTree {

    private static final long serialVersionUID = 1L;

    private Integer orgId;

    private Integer platformEnterpriseId;

    private String orgLevel;

    private Integer superiorOrg;

    private String name;

    private Date createDate;

    private Integer isDepartment;

    private String state;

}
