package com.hq.loggetter.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hq.common.core.domain.BaseEntity;

import lombok.Data;

/**
 * 
 * 机器集群信息表    biz_machine_group_info
 * @author huzj
 *
 */
public class Machine extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3245890302772589533L;

	private int id;
	
	private String groupName;
	
	private String ip;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("groupName", getGroupName())
                .append("ip", getIp())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
	}
	
	

}
