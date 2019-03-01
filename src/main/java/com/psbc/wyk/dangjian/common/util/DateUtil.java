/*
 * Copyright (C) 2017 eKing Technology, Inc. All Rights Reserved.
 */

package com.psbc.wyk.dangjian.common.util;


import com.psbc.wyk.dangjian.common.exception.GlobalException;
import com.psbc.wyk.dangjian.common.responsecode.CodeDefault;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 适合各种时间转换的 DateUtil
 * 后续改为 joda-time 来实现
 */
@Component
public class DateUtil {

    /**
     * date 转化成 String
     *
     * @param date
     * @return
     */
    public static String toStringYMD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * date 转化成 String
     *
     * @param date
     * @return
     */
    public static String toStringYMDHMS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * string 转化成 date
     *
     * @param str
     * @return
     */
    public static Date toDateYMD(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            throw new GlobalException(CodeDefault.ILLEGAL_DATE_FORMAT, e);
        }
    }
    /**
     * string 转化成 date
     *
     * @param str
     * @return
     */
    public static Date toDateYMDHMS(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            throw new GlobalException(CodeDefault.ILLEGAL_DATE_FORMAT, e);
        }
    }
}