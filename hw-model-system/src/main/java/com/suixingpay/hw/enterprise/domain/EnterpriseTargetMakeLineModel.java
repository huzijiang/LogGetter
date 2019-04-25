package com.suixingpay.hw.enterprise.domain;

import lombok.Data;

import java.util.Date;

/**
 * @description: 企业指标标记线模板
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 23:43
 **/
@Data
public class EnterpriseTargetMakeLineModel {

	private static final long serialVersionUID = 1L;

	private Integer enterpriseTargetMakeLineModelId;

	private Integer enterpriseReportId;

	private String targetMakeLineModelId;

	private Integer targetModelTemplateId;

	private Integer enterpriseId;

	private Integer targetModelId;

	private String lineName;

	private String description;

	private String markValue;

	private Date lastChangeDate;

	private Integer lastChanger;

}