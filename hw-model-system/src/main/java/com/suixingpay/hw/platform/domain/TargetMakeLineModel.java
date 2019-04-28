package com.suixingpay.hw.platform.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 指标标记线模板
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 23:43
 **/
@Data
public class TargetMakeLineModel {

	private static final long serialVersionUID = 1L;

	private String targetMakeLineModelId;

	private Integer targetModelId;

	private Integer targetModelTemplateId;

	private String lineName;

	private String description;

	private BigDecimal defaultValue;

}