/*
 * Copyright (C) 2016 eKing Technology, Inc. All Rights Reserved.
 */
package com.psbc.wyk.dangjian.common.responsecode;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回的错误代码, 返回前端用 int, 后端用枚举, 可以减少数据传输
 * 可以基于 code 的 name 进行国际化
 * 这里只列出系统默认的 code, 其他的 code, 应该按 CodePerson 这样进行区分
 * 建议划分方法, 使用 9 位数字 (一个 int 可以表示完全), 第一位固定是 1, 接下来 4 位划分模块, 最后四位划分具体的 errorCode;
 * 如: 10002xxxx, 表示用户相关模块错误
 */
@AllArgsConstructor
@Getter
public enum CodePerson implements CodeEnum {
    /**
     *
     */
    PERSON_EXISTS_EXCEPTION(100020001, "用户已存在"),
    PERSON_NOT_EXISTS_EXCEPTION(100020002, "用户不存在");

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