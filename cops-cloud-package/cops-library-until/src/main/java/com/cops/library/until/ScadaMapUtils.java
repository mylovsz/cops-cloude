package com.cops.library.until;

import com.cops.library.comparator.StringComparator;
import com.google.common.collect.MapConstraints;
import com.xiaoleilu.hutool.map.MapUtil;

import java.util.Comparator;
import java.util.Map;

/**
 * 系统关于Map
 *
 * @Author: wangermao
 * @Date: 2020-05-12 10:37
 * @Version: V1.0
 */
public class ScadaMapUtils {

    /**
     * Map的key模糊查询
     * @param map
     * @param key
     * @return
     */
    public static Map<String, String> MapLike(Map<String, String> map, String key){
        Map<String, String> result = MapUtil.newTreeMap(new StringComparator());

        for(Map.Entry<String, String> entry : map.entrySet()){
            if(entry.getKey().indexOf(key) > -1){
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }
}
