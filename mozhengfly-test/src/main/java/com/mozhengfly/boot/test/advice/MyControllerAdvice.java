package com.mozhengfly.boot.test.advice;

import com.mozhengfly.boot.test.vo.ResultInfo;
import com.mozhengfly.boot.verification.ParamVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description MyControllerAdvice
 * @Author mozhengfly
 * @Date 2019-07-01 22:41:14
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Slf4j
@ControllerAdvice
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = ParamVerificationException.class)
    public ResponseEntity<ResultInfo> errorHandler(Exception e) {
        log.error("ParamVerificationException...", e);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(1000);
        resultInfo.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultInfo);
    }
}
