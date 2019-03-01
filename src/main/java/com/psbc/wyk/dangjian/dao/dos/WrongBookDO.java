package com.psbc.wyk.dangjian.dao.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 错题本
 * @author wyk on 2019/02/27
 */
@Data
@TableName("wrongbook")
public class WrongBookDO {
    /**
     * id
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;

    private Long uid;

    private Long qid;

    /**
     * 0:正常，1:删除
     */
    private Integer type;

}
