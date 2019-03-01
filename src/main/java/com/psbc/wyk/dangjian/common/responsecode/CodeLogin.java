package com.psbc.wyk.dangjian.common.responsecode;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wyk on 2019/02/28
 */
@AllArgsConstructor
@Getter
public enum CodeLogin implements CodeEnum{

    /**
     *
     */
    USER_PHONE_EMPTY_EXCEPTION(100030001, "请输入账号"),
    USER_PASSWORD_EMPTY_EXCEPTION(100030002, "请输入密码"),
    JWT_TOKEN_ERROR_EXCEPTION(100030003, "该JwtToken并不适用于登录"),

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
