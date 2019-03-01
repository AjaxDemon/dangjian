/*
 * Copyright (C) 2016 eKing Technology, Inc. All Rights Reserved.
 */
package com.psbc.wyk.dangjian.common.responsecode;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;

/**
 * 返回的错误代码, 返回前端用 int, 后端用枚举, 可以减少数据传输
 * 可以基于 code 的 name 进行国际化
 * 这里只列出系统默认的 code, 其他的 code, 应该按 CodePerson 这样进行区分
 * 建议划分方法, 使用 9 位数字 (一个 int 可以表示完全), 第一位固定是 1, 接下来 4 位划分模块, 最后四位划分具体的 errorCode;
 * 如: 100010001, 表示系统错误
 *
 * TODO: defaultMessage, 应该考虑带参数的情况
 */
@AllArgsConstructor
public enum CodeDefault implements CodeEnum {
    /**
     *
     */
    OK(0, "成功"),
    // 服务器端错误, 以 100010001 开头
    PARTIAL_SUCCESS(100010001, "部分成功"),
    INTERNAL_SERVER_ERROR(100010002, "未处理异常"),
    ILLEGAL_ARGUMENT(100010003, "客户端输入参数错误"),
    PRIMARY_ID_ERROR(100010004, "主键生成错误"),
    ILLEGAL_DATE_FORMAT(100010005, "日期格式错误"),
    LOGIN(100010006, "请登录"),
    LOGIN_TIMEOUT(100010007, "登录信息已过期，请重新登录"),

    ;

    // 返回客户端的编码
    private final int code;

    // 默认消息
    private final String defaultMessage;

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getDefaultMessage() {
        return this.defaultMessage;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("defaultMessage", defaultMessage)
                .toString();
    }
}