/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.web.converts
 * @className com.mozhengfly.boot.web.translate.FastJsonAfterFilter
 * @copyright Copyright 2021 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.translator;

import com.alibaba.fastjson.serializer.AfterFilter;
import com.mozhengfly.boot.tool.util.ClassUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * FastJsonAfterFilter
 *
 * @Description FastJsonAfterFilter
 * @Author wangchonglin
 * @Date 2021-06-06 13:50:54
 * @Version 1.0.0
 */
@Component
public class FastJsonAfterFilter extends AfterFilter {

    private TranslateProperties translateProperties;

    private List<ITranslator<?>> translators;

    private final HashMap<Class<? extends Annotation>, ITranslator<?>> maps = new HashMap<>();

    @Autowired
    public void setTranslateProperties(TranslateProperties translateProperties) {
        this.translateProperties = translateProperties;
    }

    @Autowired
    public void setTranslators(List<ITranslator<?>> translators) {
        this.translators = translators;
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        if (!CollectionUtils.isEmpty(this.translators)) {
            this.translators.forEach(item -> {
                ParameterizedType parameterizedType = (ParameterizedType) (item.getClass().getGenericInterfaces()[0]);
                maps.put((Class<? extends Annotation>) (parameterizedType.getActualTypeArguments()[0]), item);
            });
        }
    }

    @Override
    public void writeAfter(Object object) {
        Field[] fileds = ClassUtil.getFileds(object.getClass());
        if (ArrayUtils.isNotEmpty(fileds)) {
            for (Field field : fileds) {
                Object filedValue = ClassUtil.getFiledValue(object, field);
                if (filedValue == null) {
                    continue;
                }
                Annotation annotation = getTranslatorAnnotation(field);
                if (annotation != null) {
                    ITranslator<?> translator = this.maps.get(annotation.annotationType());
                    String text = translator.translate(new TranslateParam(object, filedValue, annotation));
                    writeKeyValue(field.getName() + translateProperties.getSuffix(), text);
                }
            }
        }
    }

    /**
     * 获取字段上的翻译注解
     *
     * @param field 字段
     * @return 注解
     */
    private Annotation getTranslatorAnnotation(Field field) {
        for (Class<? extends Annotation> clazz : this.maps.keySet()) {
            Annotation annotation = field.getAnnotation(clazz);
            if (Objects.nonNull(annotation)) {
                return annotation;
            }
        }
        return null;
    }
}
