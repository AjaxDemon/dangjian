package com.psbc.wyk.dangjian.common.util;



import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;


import java.security.MessageDigest;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author piers-wu
 */
@Slf4j
public class Md5Encoder {

    @Setter
    @Accessors(chain = true)
    private String signfield = "sign";

    @Setter
    @Accessors(chain = true)
    private String[] ignores = new String[]{"paySign"};

    @Setter
    @Accessors(chain = true)
    private String key = "123456789";

    public static Md5Encoder createPooulpayMd5Encoder() {
        return new Md5Encoder();
    }

    public String getSign(Map<String, Object> params) {
        Map<String, Object> map = new TreeMap<String, Object>(new Comparator<String>() {
            @Override
            public int compare(String obj1, String obj2) {
                return obj1.compareTo(obj2);
            }
        });
        map.putAll(params);

        Set<Entry<String, Object>> entrySet = map.entrySet();
        List<String> paramArray = new ArrayList<>();
        for (Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            if (signfield.equals(key) || ArrayUtils.contains(ignores, key)) {
                continue;
            }
            Object value = entry.getValue();
            if (value == null || StringUtils.EMPTY.equals(value)) {
                continue;
            }
            paramArray.add(String.format("%s=%s", key, value));
        }
        paramArray.add(String.format("key=%s", key));

        String paramStr = String.join("&", paramArray);
        System.out.println(paramStr);

        String md5 = DigestUtils.md5Hex(paramStr);

        return md5.toUpperCase();
    }

    public void encode(Map<String, Object> params) {
        params.put(signfield, getSign(params));
    }

    public boolean matches(Map<String, Object> params, String sign) {

        if (StringUtils.isBlank(sign)) {
            return false;
        }

        String paramsign = getSign(params);
        return sign.equals(paramsign);
    }


    public static String md5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
            }
        } catch (Exception e) {
            log.error("" + e);
        }
        return resultString;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static final String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


    public static void main(String[] args) {
        System.out.println(Md5Encoder.md5Encode("123456", null));
        System.out.println(Md5Encoder.md5Encode("123456", Charsets.UTF_8.toString()));
    }
}
