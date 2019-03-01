package com.psbc.wyk.dangjian.config;

import com.psbc.wyk.dangjian.common.exception.GlobalException;
import com.psbc.wyk.dangjian.common.responsecode.CodeDefault;
import com.psbc.wyk.dangjian.common.util.JwtUtil;
import com.psbc.wyk.dangjian.interfaces.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.psbc.wyk.dangjian.common.Constant.*;
import static com.psbc.wyk.dangjian.common.responsecode.CodeLogin.JWT_TOKEN_ERROR_EXCEPTION;

/**
 * @author wyk on 2019/02/28
 */
@Component
public class JwtTokenStrategy implements TokenLoginStrategy {
    @Override
    public Account getAuthetication(HttpServletRequest req) {
        String jwtToken = req.getHeader(JWT_TOKEN);
        if (jwtToken == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        try {
            result = JwtUtil.decryption(jwtToken);
        } catch (Exception e) {
            throw new GlobalException(CodeDefault.LOGIN_TIMEOUT, e);
        }
        String purpose = result.get("purpose");
        if (!JwtUtil.LOGIN_TOKEN.equals(purpose)) {
            throw new GlobalException(JWT_TOKEN_ERROR_EXCEPTION, null);
        }
        String role = result.get("authorities");
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(role));

        String message = result.get("message");
        String[] split = message.split("#");
        return new Account(Long.parseLong(split[0]), split[1], "empty", list);
    }
}
