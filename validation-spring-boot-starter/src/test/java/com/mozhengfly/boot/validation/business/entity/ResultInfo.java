package com.mozhengfly.boot.validation.business.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ResultInfo
 * @Description ResultInfo
 * @Author wangchonglin
 * @Date 2020-01-07 14:01:12
 * @Version 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultInfo {

    private int code;

    private String message;
}
