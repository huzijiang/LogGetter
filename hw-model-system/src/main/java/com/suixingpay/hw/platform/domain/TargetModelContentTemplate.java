package com.suixingpay.hw.platform.domain;

import lombok.Data;

/**
 * @description: 平台指标模型内容模板
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

    private String fullTemplateContent;

    private String belongQueue;

    private Integer sn;
}
