package com.psbc.wyk.dangjian.dao.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户表
 * @author wyk on 2019/02/27
 */
@Data
@TableName("user")
public class UserDO {

    /**
     * id
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;

    private String phone;

    private String password;

    @TableField("nick_name")
    private String nickName;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    /**
     * 0:管理员 1:普通用户
     */
    private Integer type;

}
