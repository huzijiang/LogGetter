package com.suixingpay.hw.web.util;

import com.suixingpay.hw.system.mapper.CommonMapper;
import com.suixingpay.hw.web.controller.common.CommonController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname IdUtil
 * @Description 获取不同数据库主键的方法
 * @Date 2019/4/11 15:10
 * @Created liuyan[liu_yan@suixingpay.com]
 */
public class IdUtil {

    public static List getManyId(String tableName, int idCount) {
        CommonMapper commonMapper = SpringContextUtil.getBean(CommonMapper.class);
        int starCount = commonMapper.getManyId(tableName, idCount);
        ArrayList list = new ArrayList(idCount);
        for (int i = 0; i < idCount; i++) {
            list.add(starCount++);
        }
        return list;
    }

}

