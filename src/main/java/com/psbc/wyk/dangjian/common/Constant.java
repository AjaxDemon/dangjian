package com.psbc.wyk.dangjian.common;

import lombok.Data;

/**
 * @author wyk on 2019/02/28
 */
@Data
public class Constant {

    /**
     * 管理员用户0
     */
    public static final Integer USER_TYPE_ADMIN = 0;
    public static final Integer USER_TYPE_NORMAL = 1;

    public static final String TYPE_ADMIN = "admin";
    public static final String TYPE_NORMAL = "normal";

    public static final String JWT_TOKEN = "jwtToken";


}
