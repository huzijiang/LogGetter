package com.suixingpay.hw.enterprise.domain;

import com.suixingpay.hw.common.annotation.Excel;
import com.suixingpay.hw.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * @Classname Customer
 * @Description 客户实体类
 * @Date 2019/4/10 13:51
 * @Created liuyan[liu_yan@suixingpay.com]
 */
public class Enterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 企业ID */
    private Integer enterpriseId;

    /** 企业名称 */
    private String name;

    /** 企业地址 */
    private String address;

    /** 企业身份证号 */
    private String uscd;

    /** 创建日期 */
    private String createDate;

    /** 创建人 */
    private Integer creater;

    /** 企业管理员id */
    private Integer admin;

    /** 企业头像 */
    private String logo;

    public static boolean isAdmin(int enterpriseId)
    {
        return enterpriseId != 0 && 1L == enterpriseId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUscd() {
        return uscd;
    }

    public void setUscd(String uscd) {
        this.uscd = uscd;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "enterpriseId=" + enterpriseId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", uscd='" + uscd + '\'' +
                ", createDate='" + createDate + '\'' +
                ", creater=" + creater +
                ", admin=" + admin +
                ", logo='" + logo + '\'' +
                '}';
    }
}

