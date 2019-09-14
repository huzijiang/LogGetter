package com.hq.web.util;

import com.hq.system.mapper.CommonMapper;
import com.hq.web.controller.common.CommonController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname IdUtil
 * @Description 获取不同数据库主键的方法
 * @Date 2019/4/11 15:10
 * @Created huzj
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

