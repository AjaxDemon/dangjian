package com.psbc.wyk.dangjian.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 权限工具类
 *
 * @author piers-wu
 */
public class SecurityUtils {

	/**
	 * 获取当前登陆用户对象
	 *
	 * @return
	 */
	public static Account getLoginAccount() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal == null) {
			return null;
		}
		if (principal instanceof Account) {
			return (Account) principal;
		}
		return null;
	}

	/**
	 * 获取当前登陆用户的id，如果没有登陆则报错
	 *
	 * @return
	 */
	public static Long getLoginAccountId() {
		Account loginAccount = getLoginAccount();
		if (loginAccount == null) {
			return -1L;
		}
		return loginAccount.getAccountId();
	}

	/**
	 * 注册并发布授权信息
	 *
	 * @param account
	 * @param sc
	 * @param request
	 * @throws AccessDeniedException
	 */
	public static void registerAuth(User account, ServletContext sc, HttpServletRequest request)
			throws AccessDeniedException {
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(account, "empty",
				account.getAuthorities());
		setDetails(request, auth);

		SecurityContextHolder.getContext().setAuthentication(auth);
		HttpSession session = request.getSession();
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
		webApplicationContext.publishEvent(new InteractiveAuthenticationSuccessEvent(auth, SecurityUtils.class));

	}

	public static void registerAuth(User account) {
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(account, "empty",
				account.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	protected static void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		WebAuthenticationDetails webAuthenticationDetails = new WebAuthenticationDetails(request);
		authRequest.setDetails(webAuthenticationDetails);
	}
}
