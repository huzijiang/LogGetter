package com.suixingpay.hw.enterprise.domain;

import lombok.Data;

import java.util.Date;

/**
 * 企业入驻申请信息
 */
@Data
public class EnterpriseJoinApply {

	/**
	 * NOT NULL true
	 * DomainName 
	 * DatatypeName CHAR
	 * DataTypeLength/Precision 10
	 */
	private Integer enterpriseJoinApplyId;

	/**
	 * 企业名称
	 * 
	 * NOT NULL true
	 * DomainName 
	 * DatatypeName CHAR
	 * DataTypeLength/Precision 40
	 */
	private String enterpriseName;

	/**
	 * 联系人
	 * 
	 * NOT NULL true
	 * DomainName 
	 * DatatypeName CHAR
	 * DataTypeLength/Precision 10
	 */
	private String linkMan;

	/**
	 * NOT NULL true
	 * DomainName 
	 * DatatypeName CHAR
	 * DataTypeLength/Precision 10
	 */
	private String duty;

	/**
	 * NOT NULL true
	 * DomainName 
	 * DatatypeName CHAR
	 * DataTypeLength/Precision 10
	 */
	private String phone;

	/**
	 * NOT NULL true
	 * DomainName 
	 * DatatypeName CHAR
	 * DataTypeLength/Precision 30
	 */
	private String email;

	/**
	 * 跟进状态
	 * 
	 * NOT NULL true
	 * DomainName EnterpriseJoinApplyFollowState
	 * DatatypeName CHAR
	 * DataTypeLength/Precision 2
	 */
	private String followState;

	/**
	 * 受理人，这里是指后台系统管理人员
	 * 
	 * NOT NULL false
	 * DomainName 
	 * DatatypeName INT
	 * DataTypeLength/Precision 
	 */
	private int acceptor;

	/**
	 * 申请时间
	 * 
	 * NOT NULL true
	 * DomainName 
	 * DatatypeName DATE
	 * DataTypeLength/Precision 
	 */
	private Date applyDate;

}
