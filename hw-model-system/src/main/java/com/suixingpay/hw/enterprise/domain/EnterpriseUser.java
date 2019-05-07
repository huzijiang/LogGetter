package com.suixingpay.hw.enterprise.domain;

import com.suixingpay.hw.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * @Classname EnterpriseUser
 * @Description 企业用户信息表
 * @Date 2019/4/11 11:10
 * @Created liuyan[liu_yan@suixingpay.com]
 */
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getWxChatHead() {
        return wxChatHead;
    }

    public void setWxChatHead(String wxChatHead) {
        this.wxChatHead = wxChatHead;
    }

    public String getQicq() {
        return qicq;
    }

    public void setQicq(String qicq) {
        this.qicq = qicq;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "EnterpriseUser{" +
                "userId=" + userId +
                ", enterpriseId=" + enterpriseId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", state='" + state + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", wxOpenId='" + wxOpenId + '\'' +
                ", wxChatHead='" + wxChatHead + '\'' +
                ", qicq='" + qicq + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
