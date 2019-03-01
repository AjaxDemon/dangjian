package com.psbc.wyk.dangjian.common.responsecode;

public interface CodeEnum {
    /**
     * 获取枚举的 Name
     *
     * @return
     */
    String getName();

    /**
     * 获取 code
     *
     * @return
     */
    long getCode();

    /**
     * 获取默认消息
     *
     * @return
     */
    String getDefaultMessage();


}
