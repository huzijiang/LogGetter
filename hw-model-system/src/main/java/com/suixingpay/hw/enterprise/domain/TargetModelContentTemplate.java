package com.suixingpay.hw.enterprise.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 23:56
 **/
@Data
public class TargetModelContentTemplate {

    private static final long serialVersionUID = 1L;

    private Integer targetModelTemplateId;

    private Integer targetModelId;

    private String name;

    private String showWay;

    private String templateContent;

    private String belongQueue;

    private Integer sn;

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
