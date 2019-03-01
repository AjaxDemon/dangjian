package com.psbc.wyk.dangjian.interfaces.base;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 不计算总页数的分页对象
 * 
 * @author piers-wu
 *
 * @param <T>
 */
public class NoSearchCountPage<T> extends Page<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSearchCountPage() {
		super();
		this.setSearchCount(false);
	}

	public NoSearchCountPage(int current, int size) {
		super(current, size);
		this.setSearchCount(false);
	}

}
