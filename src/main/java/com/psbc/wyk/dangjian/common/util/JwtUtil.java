package com.psbc.wyk.dangjian.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wyk on 2018/06/15
 */
public class JwtUtil {

    // 100分钟
    public static final long ONE_HANDRUM_EXPIRATIONTIME = 1000 * 60 * 100;
    // JWT密码
    private static final String SECRET = "PSBC@DangJian#Yuxun";

    //目的地
    public static final String LOGIN_TOKEN = "loginToken";


    /**
     * 传入message生成jwt加密
     *
     * @param message
     * @return
     */
    public static String generateToken(String message, String purpose, String role, long time) {
        return Jwts.builder()
//                // 保存权限（角色）
                .claim("authorities", role)
                // 用户名写入标题
                .setSubject(message)
                .setIssuer(purpose)
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + time))
                // 签名设置
                .signWith(SignatureAlgorithm.HS384, SECRET)
                .compact();
    }

    /**
     * 验证jwt，并解析message
     *
     * @param jwtToken
     * @return
     */
    public static Map<String, String> decryption(String jwtToken) throws Exception {
        // 解析 Token
        Claims claims = Jwts.parser()
                // 验签
                .setSigningKey(SECRET)
                // 去掉 Bearer
                .parseClaimsJws(jwtToken)
                .getBody();
        Map<String, String> result = new HashMap<>(2);
        result.put("message", claims.getSubject());
        result.put("purpose", claims.getIssuer());
        result.put("authorities", claims.get("authorities") + "");
        return result;
    }


    public static void main(String[] args) {
        String a = JwtUtil.generateToken("18789536667#1", JwtUtil.LOGIN_TOKEN, "admin", JwtUtil.ONE_HANDRUM_EXPIRATIONTIME);
        System.out.println(a);
        try {
            System.out.println(JwtUtil.decryption(a));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
