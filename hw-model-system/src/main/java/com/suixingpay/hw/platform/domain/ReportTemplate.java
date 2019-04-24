package com.suixingpay.hw.platform.domain;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 报告模板
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 15:28
 **/
@Data
public class ReportTemplate {

    private static final long serialVersionUID = 1L;

    private Integer reportTemplateId;

    private String name;

    private String description;

    private Integer creater;

    private Date createDate;

    private String state;

    private String audiences;

    private String belongOrgLevel;

    private String type;

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
