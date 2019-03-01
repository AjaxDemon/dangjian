package com.psbc.wyk.dangjian.common.util;


import com.psbc.wyk.dangjian.common.exception.GlobalException;
import com.psbc.wyk.dangjian.common.responsecode.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 断言类
 *
 * @author piers-wu
 */
@Slf4j
public class Assert {


    /**
     * 非空判断
     * @param object
     * @param codeEnum
     * @param cause
     */
    public static void notNull(@Nullable Object object, CodeEnum codeEnum, Throwable cause) {
        boolean isNull = false;
        if (object instanceof String) {
            if (!StringUtils.isNotBlank((String) object)) {
                isNull = true;
            }
        } else if (object == null) {
            isNull = true;
        }

        if (isNull) {
            throw new GlobalException(codeEnum, cause);
        }
    }

    public static void notNull(@Nullable Object object, CodeEnum codeEnum) {
        notNull(object, codeEnum, null);
    }


    /**
     * 数组类型对象非空判断
     *
     * @param collection
     * @param codeEnum
     */
    public static void notEmpty(@Nullable Collection<?> collection, CodeEnum codeEnum) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new GlobalException(codeEnum,null);
        }
    }

    /**
     * 对象相等判断
     *
     * @param obj1
     * @param obj2
     * @param codeEnum
     */
    public static void isEquals(Object obj1, Object obj2, CodeEnum codeEnum) {
        boolean equals = (obj1 == null && obj2 == null) || (obj1 != null && obj1.equals(obj2));

        if (!equals) {
            throw new GlobalException(codeEnum,null);
        }
    }

    /**
     * 对象不相等判断
     *
     * @param obj1
     * @param obj2
     * @param codeEnum
     */
    public static void isNotEquals(Object obj1, Object obj2, CodeEnum codeEnum) {
        boolean equals = (obj1 == null && obj2 == null) || (obj1 != null && obj2 != null && obj1.equals(obj2));

        if (!equals) {
            throw new GlobalException(codeEnum,null);
        }
    }


    public static void isTrue(boolean b, CodeEnum codeEnum) {
        if (!b) {
            throw new GlobalException(codeEnum, null);
        }
    }


}
