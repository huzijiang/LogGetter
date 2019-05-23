package com.suixingpay.hw.enterprise.domain;

import com.suixingpay.hw.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Classname EnterpriseUser
 * @Description 企业用户信息表
 * @Date 2019/4/11 11:10
 * @Created liuyan[liu_yan@suixingpay.com]
 */
@Data
public class EnterpriseUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 企业管理员ID */
    private Integer userId;

    /** 所属企业ID */
    private Integer enterpriseId;

    /** 账号 = 邮箱 */
    private String account;

    /** 密码 */
    private String password;

    /** 姓名 */
    private String name;

    /** 电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 状态  00:正常  01:失效-企业服务已过期导致  02：冻结-企业管理员主动冻结 */
    private String state;

    /** 工号 */
    private String jobNumber;

    /** 微信号 */
    private String wxOpenId;

    /** 微信头像 */
    private String wxChatHead;

    /** qq号 */
    private String qicq;

    /** 创建时间 */
    private Date createDate;

    private Date wxBindingTime;
}
