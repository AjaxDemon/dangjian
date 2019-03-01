package com.psbc.wyk.dangjian.interfaces.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author wyk on 2019/02/28
 */
@Data
public class UserDTO {

    private String phone;

    private String password;

    private Long id;

    private String nickName;

    private Date createTime;

    private Date updateTime;

    /**
     * 0:管理员 1:普通用户
     */
    private Integer type;

}
