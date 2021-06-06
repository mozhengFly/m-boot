/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.web.translator
 * @className com.mozhengfly.boot.web.translator.TranslateUtil
 * @copyright Copyright 2021 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.translator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * TranslateUtil
 *
 * @Description 翻译的工具类
 * @Author wangchonglin
 * @Date 2021-06-06 16:13:45
 * @Version 1.0.0
 */
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TranslateUtil {

    private static final SerializerFeature[] FEATURES = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty,
            // MAP为null输出空
            SerializerFeature.WriteMapNullValue
    };

    private static FastJsonAfterFilter fastJsonAfterFilter;

    @Autowired
    public void setFastJsonAfterFilter(FastJsonAfterFilter fastJsonAfterFilter) {
        TranslateUtil.fastJsonAfterFilter = fastJsonAfterFilter;
    }

    /**
     * 转换bean
     *
     * @param object 对象
     * @return map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> transformBean(Object object) {
        if (Objects.isNull(object)) {
            return new HashMap<>();
        }
        String s = JSON.toJSONString(object, fastJsonAfterFilter, FEATURES);
        return JSON.parseObject(s, Map.class);
    }

    /**
     * 转换List
     *
     * @param list List
     * @param <T>  泛型
     * @return list
     */
    @SuppressWarnings("unchecked")
    public static <T> List<Map<String, String>> transformList(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        String s = JSON.toJSONString(list, fastJsonAfterFilter, FEATURES);
        return JSON.parseObject(s, List.class);
    }
}
