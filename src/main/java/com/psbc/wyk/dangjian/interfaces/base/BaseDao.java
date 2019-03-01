package com.psbc.wyk.dangjian.interfaces.base;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description :
 * ---------------------------------
 * @Author : Liang.Guangqing
 * @Date : Create in 2017/11/2 22:27
 */
public interface BaseDao<T> extends BaseMapper<T> {

	T lockById(Serializable id);
	List<T> lockList(@Param("ew") Wrapper<T> wrapper);
	List<T> lockPage(RowBounds rowBounds, @Param("ew") Wrapper<T> wrapper);
	List<T> lockBatchIds(@Param("coll") Collection<? extends Serializable> idList);

}
