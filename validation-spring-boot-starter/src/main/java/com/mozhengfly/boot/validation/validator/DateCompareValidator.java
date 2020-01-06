package com.mozhengfly.boot.validation.validator;

import com.mozhengfly.boot.validation.annotation.DateCompare;
import com.mozhengfly.boot.validation.constants.OperatorEnum;
import com.mozhengfly.boot.validation.exception.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * DateCompareValidator
 *
 * @Description DateCompareValidator
 * @Author wangchonglin
 * @Date 2020-01-06 16:49:45
 * @Version 1.0.0
 */
@Slf4j
public class DateCompareValidator implements ConstraintValidator<DateCompare, Object> {

    private String start;

    private String end;

    private OperatorEnum operatorEnum;

    @Override
    public void initialize(DateCompare constraintAnnotation) {
        this.start = constraintAnnotation.start();
        this.end = constraintAnnotation.end();
        this.operatorEnum = constraintAnnotation.operator();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Object startValue = PropertyUtils.getProperty(value, this.start);
            Object endValue = PropertyUtils.getProperty(value, this.end);
            // 只要有一个为空就是true
            if (ObjectUtils.isEmpty(startValue) || ObjectUtils.isEmpty(endValue)) {
                return true;
            }
            LocalDate startDate;
            LocalDate endDate;
            if (startValue instanceof LocalDate && endValue instanceof LocalDate) {
                startDate = (LocalDate) startValue;
                endDate = (LocalDate) endValue;
            } else if (startValue instanceof String && endValue instanceof String) {
                startDate = string2LocalDate((String) startValue);
                endDate = string2LocalDate((String) endValue);
            } else if (startValue instanceof Date && endValue instanceof Date) {
                startDate = date2LocalDate((Date) startValue);
                endDate = date2LocalDate((Date) endValue);
            } else if (startValue instanceof LocalDateTime && endValue instanceof LocalDateTime) {
                LocalDateTime startTime = (LocalDateTime) startValue;
                LocalDateTime endTime = (LocalDateTime) endValue;
                if (isLess()) {
                    return startTime.isBefore(endTime);
                } else {
                    return startTime.isBefore(endTime) || startTime.isEqual(endTime);
                }
            } else {
                throw new CustomValidationException("start和end字段类型不正确");
            }
            if (isLess()) {
                return startDate.isBefore(endDate);
            } else {
                return startDate.isBefore(endDate) || startDate.isEqual(endDate);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new CustomValidationException("获取属性值出错", e);
        }
    }

    /**
     * 是否是小于比较
     *
     * @return
     */
    private boolean isLess() {
        return OperatorEnum.LESS.getOperator().equals(this.operatorEnum.getOperator());
    }

    /**
     * String to LocalDate
     *
     * @param date
     * @return
     */
    private LocalDate string2LocalDate(String date) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, fmt);
    }

    /**
     * Date to LocalDate
     *
     * @param date date
     * @return
     */
    private LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        return localDate;
    }
}
