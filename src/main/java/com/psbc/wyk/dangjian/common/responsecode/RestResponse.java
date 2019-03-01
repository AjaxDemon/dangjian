/*
 * Copyright (C) 2016 eKing Technology, Inc. All Rights Reserved.
 */
package com.psbc.wyk.dangjian.common.responsecode;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.base.MoreObjects;
import com.psbc.wyk.dangjian.common.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Rest 请求返回值
 *
 * @author zengfan
 */
@Data
// 禁止使用构造函数创建 RestResponse, 只能是 success 或者 exception
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(Include.NON_NULL)
@ApiModel
public class RestResponse<T> implements Serializable {

    @ApiModelProperty(value = "执行状态码, 执行成功返回 0, 其他的都是 EXCEPTION")
    private long code;

    @ApiModelProperty(value = "消息, OK 的时候, message 一般不填")
    private String message = null;

    @ApiModelProperty(value = "具体的数据, EXCEPTION 的时候, data 一般 不填")
    private T data = null;

    @ApiModelProperty(value = "时间戳, 只在 exception 时有用")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp = null;

    @ApiModelProperty(value = "放一些辅助信息 key->value, 一般用来进一步描述异常信息, OK 时暂未用到")
    private Map<String, Object> extraInfo = null;

    /**
     * 表示调用成功
     *
     * @param data
     *
     * @return
     */
    public static <T> RestResponse<T> ok(T data) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.code = CodeDefault.OK.getCode();
        restResponse.data = data;
        return restResponse;
    }

    /**
     * 调用发生 异常
     *
     * @param codeEnum
     *
     * @return
     */
    public static RestResponse exception(CodeEnum codeEnum) {
        return exception(codeEnum, null);
    }

    /**
     * 调用发生 异常
     *
     * @param codeEnum
     * @param exceptionInfo
     *
     * @return
     */
    public static RestResponse exception(CodeEnum codeEnum, Map<String, Object> exceptionInfo) {
        RestResponse restResponse = new RestResponse();
        restResponse.code = codeEnum.getCode();
        restResponse.message = codeEnum.getDefaultMessage();
        // 国际化时, 使用这个
        //        restResponse.message = I18nUtil.getResponseMessage(responseCode);
        restResponse.timestamp = new Date();
        restResponse.extraInfo = exceptionInfo;
        return restResponse;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("message", message)
                .add("data", data)
                .add("timestamp", timestamp == null ? null : DateUtil.toStringYMDHMS(timestamp))
                .add("extraInfo", extraInfo)
                .toString();
    }
}
