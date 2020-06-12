package com.mozhengfly.boot.validation.business.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mozhengfly.boot.validation.annotation.ConditionalRequired;
import com.mozhengfly.boot.validation.annotation.CustomValidation;
import com.mozhengfly.boot.validation.annotation.DateCompare;
import com.mozhengfly.boot.validation.annotation.IdCard;
import com.mozhengfly.boot.validation.business.validator.PersonCustomValidator;
import com.mozhengfly.boot.validation.groups.UpdateAction;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Person
 * @Description Person
 * @Author wangchonglin
 * @Date 2020-01-07 13:40:45
 * @Version 1.0.0
 */
@ToString
@Getter
@Setter
@DateCompare(start = "createTime", end = "updateTime", message = "创建时间应该小于等于更新时间")
@ConditionalRequired.List({
    @ConditionalRequired(field = "firstName", condition = "!''.equals(lastName) && lastName != null", message = "lastName有值的时候firstName不能为空"),
    @ConditionalRequired(field = "universityDiploma", condition = "age > 18", message = "年龄大于18岁的时候，大学毕业证必填"),
    @ConditionalRequired(field = "password", condition = "limitLogin == true", message = "被限制登录的时候密码必填")
})
@CustomValidation(proxy = PersonCustomValidator.class, message = "自定义校验失败")
public class Person implements Serializable {

    @NotBlank(groups = UpdateAction.class, message = "更新Person的时候，id不能为空")
    private String id;

    private String firstName;

    private String lastName;

    @NotNull(message = "身份证不能为空")
    @IdCard(message = "身份证号码格式不正确")
    private String idCard;

    private int age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String universityDiploma;

    private boolean limitLogin;

    private String password;
}
