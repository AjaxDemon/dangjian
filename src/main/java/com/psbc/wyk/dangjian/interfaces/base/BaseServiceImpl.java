package com.psbc.wyk.dangjian.interfaces.base;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Getter;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description : ---------------------------------
 * @Author : Liang.Guangqing
 * @Date : Create in 2017/11/2 22:23
 */
public class BaseServiceImpl<M extends BaseDao<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

	@Getter
	@Autowired
	private M baseDao;

	@Override
	public boolean isExist(Serializable id) {
		this.baseDao.selectById(id);
		return this.baseDao.selectById(id) != null;
	}

	@Override
	public boolean isExist(Wrapper<T> wrapper) {
		return this.baseDao.selectCount(wrapper) >= 1;
	}

	@Override
	public Map<? extends Serializable, T> selectMapByBatchIds(Collection<? extends Serializable> idList,
			String idField) {
		List<T> selectBatchIds = this.getBaseDao().selectBatchIds(idList);
		Map<Serializable, T> map = new HashMap<>();
		try {
			for (T t : selectBatchIds) {
				Object id = PropertyUtils.getProperty(t, idField);
				map.put((Serializable) id, t);

			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}

		return map;
	}


    public T selectOne(Wrapper<T> wrapper) {

		NoSearchCountPage<T> page = new NoSearchCountPage<>(1, 1);

		IPage<T> selectPage = this.page(page, wrapper);

		List<T> records = selectPage.getRecords();
		if(records!=null&&!records.isEmpty()) {
			return records.get(0);
		}

		return null;
    }


    public T selectObj(Wrapper<T> wrapper) {
        return selectOne(wrapper);
    }


}
