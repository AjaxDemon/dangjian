package com.psbc.wyk.dangjian.common.exception;

import com.psbc.wyk.dangjian.common.responsecode.CodeEnum;
import lombok.Data;

/**
 * 全局校验 exception, 如客户端输入参数错误，这些是属于可控范围的 exception； 无需 log
 *
 * @author zengfan
 */
@Data
public class GlobalVerifyException extends RuntimeException {
    private CodeEnum codeEnum;

    public GlobalVerifyException() {
        super();
    }

    /**
     * 使用 codeEnum 的 defaultMessage 异常信息
     *
     * @param codeEnum
     */
    public GlobalVerifyException(CodeEnum codeEnum) {
        super(codeEnum.toString());
        this.codeEnum = codeEnum;
    }

    /**
     * 使用 defaultMessage + message 作为异常信息
     *
     * @param codeEnum
     * @param message
     */
    public GlobalVerifyException(CodeEnum codeEnum, String message) {
        super(String.format("%s\t%s", codeEnum.toString(), message));
        this.codeEnum = codeEnum;
    }

    /**
     * 使用 defaultMessage + message 作为异常信息
     *
     * @param codeEnum
     * @param message
     */
    public GlobalVerifyException(CodeEnum codeEnum, String message, Throwable cause) {
        super(String.format("%s\t%s", codeEnum.toString(), message), cause);
        this.codeEnum = codeEnum;
    }
}

