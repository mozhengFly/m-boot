package com.mozhengfly.boot.validation.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * OperatorEnum
 *
 * @Description 操作符
 * @Author wangchonglin
 * @Date 2020-01-06 16:53:56
 * @Version 1.0.0
 */
@Getter
@AllArgsConstructor
public enum OperatorEnum {

    /**
     * 小于
     */
    LESS("<"),
    /**
     * 小于等于
     */
    LESS_OR_EQUAL("<=");

    private String operator;
}
