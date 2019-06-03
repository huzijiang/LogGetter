package com.suixingpay.hw.enterprise.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 企业指标模板
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 23:45
 **/
@Data
public class EnterpriseTargetTemplate {

    private static final long serialVersionUID = 1L;

    private Integer enterpriseTargetTemplateId;

    private Integer enterpriseReportTemplateId;

    private Integer targetModelTemplateId;

    private Integer enterpriseId;

    private String showWay;

    private String name;

    private String templateContent;

    private String fullTemplateContent;

    private String belongQueue;

    private Integer sn;

    private String groupName;

    private String makeCycle;

}
