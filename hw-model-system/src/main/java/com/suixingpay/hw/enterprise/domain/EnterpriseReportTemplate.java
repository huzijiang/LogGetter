package com.suixingpay.hw.enterprise.domain;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 企业报告模板
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 23:43
 **/
@Data
public class EnterpriseReportTemplate {

    private static final long serialVersionUID = 1L;

    private Integer enterpriseReportTemplateId;

    private Integer enterpriseId;

    private Integer reportTemplateId;

    private String name;

    private String reportAlias;

    private String description;

    private Integer creater;

    private Date createDate;

    private String state;

    private String audiences;

    private String type;

    private Integer belongOrg;

    private String belongOrgLevel;

    private String makeCycle;

    /** 请求参数 */
    private Map<String, Object> params;

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
}
