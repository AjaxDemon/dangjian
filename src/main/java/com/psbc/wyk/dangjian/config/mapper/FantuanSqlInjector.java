package com.psbc.wyk.dangjian.config.mapper;


import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FantuanSqlInjector extends AbstractSqlInjector {


	@Override
	public List<AbstractMethod> getMethodList() {
		return Stream.of(
				new LockBatchIdsMethod(),
				new LockByIdMethod(),
				new LockListMethod(),
				new LockPageMethod()).collect(Collectors.toList());
	}
}