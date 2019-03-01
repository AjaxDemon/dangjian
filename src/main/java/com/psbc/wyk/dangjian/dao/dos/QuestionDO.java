package com.psbc.wyk.dangjian.dao.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 题目表
 * @author wyk on 2019/02/27
 */
@Data
@TableName("question")
public class QuestionDO {
    /**
     * id
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;

    /**
     * 题目
     */
    private String title;

    /**
     * 选项以#&隔开
     */
    private String choice;

    /**
     * 答案，以逗号隔开
     */
    private String answer;

    /**
     * 0:单选，1：多选，2：判断
     */
    private Integer type;

    /**
     * 解析
     */
    private String analysis;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;
}
