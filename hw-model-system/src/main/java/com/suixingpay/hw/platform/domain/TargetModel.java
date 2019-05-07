package com.suixingpay.hw.platform.domain;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 指标模板
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 15:29
 **/
@Data
public class TargetModel {
    private static final long serialVersionUID = 1L;

    private Integer reportTemplateId;

    private Integer targetModelId;

    private String name;

    private String description;

    private Integer creater;

    private Date createDate;

    private String state;

    private String level;

    private String makeCycle;

    private String childModelId;

    private String collationCode;

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
