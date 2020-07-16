package com.cops.library.until;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * 数据转换类
 */
public class DataConvert {
    /**
     * Map转换Object
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
        if(map == null)
            return null;
        ObjectMapper objectMapper = new ObjectMapper();
        Object obj = objectMapper.convertValue(map, beanClass);
        return obj;
    }

    /**
     * Object转Map
     * @param obj
     * @return
     */
    public static Map<?,?> objectToMap(Object obj){
        if(obj == null)
            return null;
        ObjectMapper objectMapper = new ObjectMapper();
        Map<?,?> map = objectMapper.convertValue(obj, Map.class);

        return map;
    }
}
