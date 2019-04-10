package com.suixingpay.hw.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.suixingpay.hw.common.core.domain.BaseEntity;

/**
 * 测试表 testtable
 * 
 * suixingpay
 * @date 2019-03-20
 */
public class Testtable extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 姓名 */
	private Integer name;
	/** 年龄 */
	private String age;
	/** 性别 */
	private String sex;
	/** 账号 */
	private String username;
	/** 密码 */
	private String passwd;
	/** 班级 */
	private String classz;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setName(Integer name) 
	{
		this.name = name;
	}

	public Integer getName() 
	{
		return name;
	}
	public void setAge(String age) 
	{
		this.age = age;
	}

	public String getAge() 
	{
		return age;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}

	public String getSex() 
	{
		return sex;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getUsername() 
	{
		return username;
	}
	public void setPasswd(String passwd) 
	{
		this.passwd = passwd;
	}

	public String getPasswd() 
	{
		return passwd;
	}
	public void setClassz(String classz) 
	{
		this.classz = classz;
	}

	public String getClassz() 
	{
		return classz;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("age", getAge())
            .append("sex", getSex())
            .append("username", getUsername())
            .append("passwd", getPasswd())
            .append("classz", getClassz())
            .toString();
    }
}
