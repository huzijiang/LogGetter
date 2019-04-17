package com.suixingpay.hw.report.domain;

import com.suixingpay.hw.report.po.ReportInfoPo;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @description: 报表信息
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-11 11:15
 **/
@Data
public class ReportInfo extends ReportInfoPo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 企业报告指标编号
     */
    private Integer enterpriseTargetDataId;

    /**
     * 报告描述内容
     */
    private String reportDescContent;

    /**
     * 报告指标数据（所有数据）
     */
    private String targetDataFullContent;

    /**
     * 报告各个指标对应的发布内容
     */
    private String targetDataPublishContent;

    /**
     * 报告各个指标对应的发布内容的编号
     */
    private Integer targetPublishInfoId;
}
