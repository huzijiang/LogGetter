package com.suixingpay.hw.report.domain;

import com.suixingpay.hw.report.po.EnterprisePo;
import lombok.Data;

import java.util.Map;

/**
 * @description: 企业信息实体类
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-11 11:43
 **/
@Data
public class Enterprise extends EnterprisePo {

    private static final long serialVersionUID = 1L;

    /**
     * 其他参数，如开始时间、结束时间
     */
    private Map<String, Object> params;
}
