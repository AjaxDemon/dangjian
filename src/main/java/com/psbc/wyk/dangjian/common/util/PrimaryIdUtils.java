/*
 * Copyright (C) 2017 eKing Technology, Inc. All Rights Reserved.
 */

package com.psbc.wyk.dangjian.common.util;


import com.psbc.wyk.dangjian.common.exception.GlobalException;
import com.psbc.wyk.dangjian.common.responsecode.CodeDefault;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;
import java.util.Locale;

/**
 * 主键生成器
 *
 * @author zengfan
 */
@Slf4j
public class PrimaryIdUtils {

    /**
     * 获取全局唯一主键，递增
     *
     * @return
     */
    public static Long getId() {

        try {
            Date date = new Date();

            // Long 的最大值是 9223372036854775807，一共 19 位
            // 以下主键，年只取最后2位，yyMMddHHmmssSSS 一共占了 15 位，后面还可以补 4 位随机数
            // 最前面的年，在 Long 不溢出的情况下，可以用到 2092年12月31日，基本够用了
            Long id = Long.parseLong(String.format("%s%04d",
                    FastDateFormat.getInstance("yyMMddHHmmssSSS", Locale.US).format(date),
                    (int) (Math.random() * 10000)));
            return id;

        } catch (Exception e) {
            throw new GlobalException(CodeDefault.PRIMARY_ID_ERROR, e);
        }
    }

    public static void main(String[] args) {
        Long id = getId();
        log.info("\nMaxLong:   {} \nPrimaryId: {}", Long.MAX_VALUE, id);
    }
}
