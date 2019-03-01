package com.psbc.wyk.dangjian.dao.repo;

import com.psbc.wyk.dangjian.dao.dos.WrongBookDO;
import com.psbc.wyk.dangjian.interfaces.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author wyk on 2019/02/27
 */
@Repository
@Mapper
public interface WrongBookMapper extends BaseDao<WrongBookDO> {
}
