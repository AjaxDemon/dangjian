/*
 * Copyright (C) 2016 eKing Technology, Inc. All Rights Reserved.
 */
package com.psbc.wyk.dangjian.common.exception;


import com.psbc.wyk.dangjian.common.responsecode.CodeDefault;
import com.psbc.wyk.dangjian.common.responsecode.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author zengfan
 */
@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 系统自定义全局异常
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public RestResponse exception(HttpServletRequest req, GlobalException e) {
        RestResponse exception;
        if (e.getExtraInfo().isEmpty()) {
            exception = RestResponse.exception(e.getCodeEnum());
        } else {
            exception = RestResponse.exception(e.getCodeEnum(), e.getExtraInfo());
        }
        log.error("GlobalException: {}", exception, e);
        return exception;
    }


    /**
     * controller 参数转化时, 主要从这里捕获错误信息
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public RestResponse exception(HttpServletRequest req, HttpMessageNotReadableException e) {
        RestResponse exception = RestResponse.exception(CodeDefault.ILLEGAL_ARGUMENT);
        log.error("HttpMessageNotReadableException: {}", exception, e);

        return exception;
    }

    /**
     * 这个兜底
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public RestResponse exception(HttpServletRequest req, RuntimeException e) {
        RestResponse exception = RestResponse.exception(CodeDefault.INTERNAL_SERVER_ERROR);
        log.error("RuntimeException: {}", exception, e);

        return exception;
    }
}
