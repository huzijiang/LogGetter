package com.suixingpay.hw.report.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 企业信息 PO
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-11 11:43
 **/
@Data
public class EnterprisePo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业编号
     */
    private Integer enterpriseId;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 企业所在地址
     */
    private String address;

    /**
     * 企业统一社会信用代码
     */
    private String uscd;

    /**
     * 注册时间
     */
    private Date createDate;

    /**
     * 创建者
     * 企业自主注册 则是 IP 注册用户账号
     * 平台后台注册 则是 当前登陆的平台管理员
     */
    private int creater;

    /**
     * 管理员账号
     */
    private int admin;

    /**
     * 企业Logo小图片
     */
    private String logoBase64;
}
