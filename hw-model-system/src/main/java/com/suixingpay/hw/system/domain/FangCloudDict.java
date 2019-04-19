package com.suixingpay.hw.system.domain;

import lombok.Data;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-18 14:21
 **/
@Data
public class FangCloudDict {
    private Integer id;
    private String domainName;
    private String domainDesc;
    private String value;
    private String valueDesc;
}
