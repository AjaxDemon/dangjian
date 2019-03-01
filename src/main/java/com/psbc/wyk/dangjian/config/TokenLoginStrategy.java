package com.psbc.wyk.dangjian.config;



import javax.servlet.http.HttpServletRequest;

/**
 * 登录策略
 */
public interface TokenLoginStrategy {

	/**
	 * 从request获取登陆账户信息
	 *
	 * @param req
	 * @return
	 */
	Account getAuthetication(HttpServletRequest req);

}
