package com.suixingpay.hw.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <功能详细描述>
 *
 * @author wenc
 * @version [版本号, 2014年8月8日 下午3:01:13 ]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class JsonUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * <功能详细描述>
     * <p>创 建 人：  wenc<br>
     * 创建时间：  2014年8月8日 下午3:09:25
     *
     * @param json
     * @param clazz
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        T t = null;
        try {
            t = JSON.parseObject(json, clazz);
        } catch (Exception e) {
            LOGGER.error("数据转换异常", e);
            throw new RuntimeException("数据转换异常");
        }
        return t;
    }

    /**
     * <功能详细描述>
     * <p>创 建 人：  wenc<br>
     * 创建时间：  2014年8月8日 下午3:34:03
     *
     * @param json
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromJson(String json) {
        Map<String, Object> obj = null;
        try {
            obj = (Map<String, Object>) JSON.parse(json);
        } catch (Exception e) {
            LOGGER.error("数据转换异常", e);
            throw new RuntimeException("数据转换异常");
        }
        return obj;
    }

    /**
     * <功能详细描述>
     * <p>创 建 人：  wenc<br>
     * 创建时间：  2014年8月8日 下午4:02:10
     *
     * @param object
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String toJson(Object object) {
        return toJson(object, null);
    }

    /**
     * <功能详细描述>
     * <p>创 建 人：  wenc<br>
     * 创建时间：  2014年8月11日 下午3:27:15
     *
     * @param object
     * @param config
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public static String toJson(Object object, SerializeConfig config) {
        if (config == null) {
            config = new SerializeConfig();
        }
        // SerializeConfig configs = new SerializeConfig();
        SerializerFeature[] features = {
            SerializerFeature.WriteMapNullValue, //空值是否输出
            SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteNullStringAsEmpty
        };
        /*if(!StringUtils.isEmpty(dateFormat)){//Date类型的转换
              configs.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
         }*/
        String result = null;
        try {

            result = JSON.toJSONString(object, config, features);
            return result;
        } catch (Exception e) {
            LOGGER.error("数据转换异常", e);
            throw new RuntimeException("数据转换异常");
        }
    }

    /**
     * <功能详细描述>
     * <p>创 建 人：  wenc<br>
     * 创建时间：  2014年8月12日 下午5:13:04
     *
     * @param data
     * @param fieldName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static List<Object> find(Map<String, Object> data, final String fieldName) {
        List<Object> result = new ArrayList<Object>();
        String[] fields = fieldName.split("\\.");
        if (fields.length == 1) {
            Object d = data.get(fields[0]);
            if (d != null) {
                result.add(d);
            }
        } else if (fields.length == 2) {
            Object d = data.get(fields[0]);
            if (d instanceof JSONArray) {
                for (Object o : (JSONArray) d) {
                    Map<String, Object> d2 = (Map<String, Object>) o;
                    String n = fields[1];
                    Object v = d2.get(n);
                    if (v != null) {
                        result.add(v);
                    }
                }
            } else {
                Map<String, Object> d2 = (Map<String, Object>) d;
                Object v = d2.get(fields[1]);
                if (v != null) {
                    result.add(v);
                }
            }
        }
        return result;

    }

    /**
     *
     * @param object
     * @param fieldName
     * @return String
     */
    public static String find2(Object object, final String fieldName) {
        PropertyFilter propertyFilter = new PropertyFilter() {
            @Override
            public boolean apply(Object source, String name, Object value) {
                if (fieldName.equals(name)) {
                    return true;
                }
                return false;
            }
        };
        JSONSerializer jsonSerializer2 = new JSONSerializer();
        jsonSerializer2.getPropertyFilters().add(propertyFilter);
        jsonSerializer2.write(object);
        return jsonSerializer2.toString();

    }

    /**
     *
     * @param json
     * @return Map<String, String>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> fromJsonToMap(String json) {
        Map<String, String> map = null;
        try {
            map = (Map<String, String>) JSON.parse(json);
        } catch (Exception e) {
            LOGGER.error("数据转换异常", e);
            throw new RuntimeException("数据转换异常");
        }
        return map;
    }

    /**
     *
     * @param json
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromJsonToMapObject(String json) {
        Map<String, Object> map = null;
        try {
            map = (Map<String, Object>) JSON.parse(json);
        } catch (Exception e) {
            LOGGER.error("数据转换异常", e);
            throw new RuntimeException("数据转换异常");
        }
        return map;
    }

}
