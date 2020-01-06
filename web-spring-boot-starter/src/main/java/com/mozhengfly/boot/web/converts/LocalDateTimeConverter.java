package com.mozhengfly.boot.web.converts;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String dateFormat2 = "yyyy/MM/dd HH:mm:ss";

    @Override
    public LocalDateTime convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = source.trim();
        try {
            SimpleDateFormat formatter;
            if (source.contains("-")) {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern(dateFormat);
                return LocalDateTime.parse(source, fmt);
            } else if (source.contains("/")) {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern(dateFormat2);
                return LocalDateTime.parse(source, fmt);
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }
        throw new RuntimeException(String.format("parser %s to Date fail", source));
    }
}
