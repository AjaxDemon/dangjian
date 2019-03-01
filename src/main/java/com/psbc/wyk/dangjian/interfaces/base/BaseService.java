package com.psbc.wyk.dangjian.interfaces.base;



import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @Description : service接口 基类
 * ---------------------------------
 * @Author : Liang.Guangqing
 * @Date : Create in 2017/11/2 15:27
 */
public interface BaseService<T> extends IService<T> {
	
	BaseDao<T> getBaseDao();
    
    boolean isExist(Serializable id);
    
    boolean isExist(Wrapper<T> wrapper);
    
    Map<? extends Serializable, T> selectMapByBatchIds(Collection<? extends Serializable> idList, String idField);
}
