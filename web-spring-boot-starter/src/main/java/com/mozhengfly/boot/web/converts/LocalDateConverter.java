package com.mozhengfly.boot.web.converts;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
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

    private static final String shortDateFormat = "yyyy-MM-dd";
    private static final String shortDateFormat2 = "yyyy/MM/dd";

    @Override
    public LocalDate convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = source.trim();
        try {
            SimpleDateFormat formatter;
            if (source.contains("-")) {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern(shortDateFormat);
                return LocalDate.parse(source, fmt);
            } else if (source.contains("/")) {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern(shortDateFormat2);
                return LocalDate.parse(source, fmt);
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }
        throw new RuntimeException(String.format("parser %s to Date fail", source));
    }
}
