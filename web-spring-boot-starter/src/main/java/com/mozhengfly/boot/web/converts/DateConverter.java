package com.mozhengfly.boot.web.converts;

import com.mozhengfly.boot.tool.constants.DateConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * DateConverter
 *
 * @Description DateConverter
 * @Author wangchonglin
 * @Date 2020-01-06 18:18:57
 * @Version 1.0.0
 */
public class DateConverter implements Converter<String, Date> {

    private static final String COLON = ":";

    @Override
    public Date convert(@NonNull String source) {
        source = StringUtils.trim(source);
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = source.replace("/", "-");
        try {
            if (source.contains(COLON)) {
                return FastDateFormat.getInstance(DateConstants.DATE_TIME_FORMAT).parse(source);
            }
            return FastDateFormat.getInstance(DateConstants.DATE_FORMAT).parse(source);
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }
    }
}
