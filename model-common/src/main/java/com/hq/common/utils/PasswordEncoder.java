package com.hq.common.utils;

import java.security.MessageDigest;

/**
 * @author Ljysama [lv_jy@hq.com]
 * @description:
 * @date 2019-04-01 16:59
 */
public class PasswordEncoder {
    private final static String[] HEXDIGITS = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String DEFAULT_SALT = "be5e0323a9195ade5f56695ed9f2eb6b036f3e6417115d0cbe2fb9d74d8740406838dc84f152014b39a2414fb3530a40bc028a9e87642bd03cf5c36a1f70801e";

    private Object salt;
    private String algorithm;

    public PasswordEncoder(Object salt, String algorithm) {
        this.salt = salt;
        this.algorithm = algorithm;
    }

    public String encode(String rawPass) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            //加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass).getBytes("utf-8")));
        } catch (Exception ex) {
        }
        return result;
    }

    public boolean isPasswordValid(String encPass, String rawPass) {
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass);

        return pass1.equals(pass2);
    }

    private String mergePasswordAndSalt(String password) {
        if (password == null) {
            password = "";
        }

        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEXDIGITS[d1] + HEXDIGITS[d2];
    }

    /**
     * 凯撒加密
     * @param arg 要加密的原文
     * @param offset 位移因子
     * @return
     */
    public static String caesar(String arg, int offset) {
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < arg.length(); i++) {
            char c = arg.charAt(i);
            // 是小写字母
            if (c >= 'a' && c <= 'z') {
                // 移动 key%26 位
                c += offset % 26;
                if (c < 'a') {
                    // 向左超界
                    c += 26;
                }
                if (c > 'z') {
                    // 向右超界
                    c -= 26;
                }
                // 是大写字母
            } else if (c >= 'A' && c <= 'Z') {
                c += offset % 26;
                if (c < 'A') {
                    c += 26;
                }
                if (c > 'Z') {
                    c -= 26;
                }
            }
            cipher.append(c);
        }
        return cipher.toString();
    }

}

