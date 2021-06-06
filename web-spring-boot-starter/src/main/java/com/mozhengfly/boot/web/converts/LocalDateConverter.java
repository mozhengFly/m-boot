package com.mozhengfly.boot.web.converts;

import com.mozhengfly.boot.tool.constants.DateConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * DateConverter
 *
 * @Description DateConverter
 * @Author wangchonglin
 * @Date 2020-01-06 18:18:57
 * @Version 1.0.0
 */
@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(@NonNull String source) {
        source = StringUtils.trim(source);
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = source.replace("/", "-");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DateConstants.DATE_FORMAT);
        return LocalDate.parse(source, fmt);
    }
}
