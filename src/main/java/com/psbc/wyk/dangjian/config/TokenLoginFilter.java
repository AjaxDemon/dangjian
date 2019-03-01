package com.psbc.wyk.dangjian.config;


import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * token登陆过滤器
 *
 * @author piers-wu
 */
public class TokenLoginFilter extends GenericFilterBean {

    @Setter
    private List<TokenLoginStrategy> tokenLoginStrategys;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            for (TokenLoginStrategy tokenLoginStrategy : tokenLoginStrategys) {
                User authetication = tokenLoginStrategy.getAuthetication(httpServletRequest);
                if (null != authetication) {
                    SecurityUtils.registerAuth(authetication, httpServletRequest.getSession().getServletContext(),
                            httpServletRequest);
                    break;
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
