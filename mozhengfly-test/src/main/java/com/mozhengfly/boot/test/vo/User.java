package com.mozhengfly.boot.test.vo;

import com.mozhengfly.boot.test.verification.UserObjectVerification;
import com.mozhengfly.boot.verification.annotation.ObjectVerification;
import com.mozhengfly.boot.verification.annotation.ParamVerification;
import com.mozhengfly.boot.verification.type.EmailVerification;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description User
 * @Author mozhengfly
 * @Date 2019-07-01 22:22:28
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Data
@ObjectVerification(UserObjectVerification.class)
public class User {

    @ParamVerification
    private String id;

    @ParamVerification(required = false, min = 2, max = 16)
    private String name;

    @ParamVerification(required = false, types = EmailVerification.class)
    private String email;

    @ParamVerification(required = false, min = 2)
    private List<String> pen;

    @ParamVerification(required = false, max = 3)
    private String[] book;

    private Date startTime;

    private Date endTime;
}
