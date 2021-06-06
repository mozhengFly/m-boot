/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.tool.util
 * @className com.mozhengfly.boot.tool.util.ClassUtil
 * @copyright Copyright 2021 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.tool.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

/**
 * ClassUtil
 *
 * @Description ClassUtil
 * @Author wangchonglin
 * @Date 2021-06-06 15:13:15
 * @Version 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassUtil {

    private static final Map<Class<?>, Field[]> cache = new ConcurrentReferenceHashMap<>();

    private static Field[] getFiledsFromCache(Class<?> clazz) {
        Field[] fields = cache.get(clazz);
        if (fields == null) {
            fields = Arrays.stream(clazz.getDeclaredFields()).filter(f -> !f.isSynthetic()).filter(f -> !"serialVersionUID".equals(f.getName())).toArray(Field[]::new);
            cache.put(clazz, fields);
        }
        return fields;
    }

    /**
     * 获取对象的所有属性
     * @param clazz 类
     * @return  属性集合
     */
    public static Field[] getFileds(Class<?> clazz) {
        Field[] fields = new Field[0];
        if (clazz == null) {
            return fields;
        }
        do {
            fields = ArrayUtils.addAll(fields, getFiledsFromCache(clazz));
            clazz = clazz.getSuperclass();
        } while (clazz != null && !Object.class.equals(clazz));
        return fields;
    }

    /**
     * 获取对象属性值
     * @param object 对象
     * @param field  字段
     * @return  值
     */
    public static Object getFiledValue(Object object, Field field) {
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field.get(object);
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}
