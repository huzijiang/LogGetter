package com.hq.system.domain;

import com.hq.common.core.domain.BaseEntity;

/**
 * @Classname Common
 * @Description uuid
 * @Date 2019/4/11 15:20
 * @Created huzj
 */
public class Common extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 主键值所在的表的名称*/
    private Integer tableName;

    /** 当前主键的值 */
    private String pkValue;

}
