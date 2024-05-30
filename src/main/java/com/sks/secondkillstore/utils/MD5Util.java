package com.sks.secondkillstore.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * @Author HQD
 * @Date 2024/4/4 14:33
 * @Version 1.0
 */
@Component
public class MD5Util {
    // 盐干扰
    private static final String salt = "1a2b3c4d";

    /**
     * MD5加密，通过依赖调包的方式进行加密
     *
     * @param src 加密的源码
     * @return DigestUtils.md5Hex(src)
     */
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    /**
     * 第一次加密，前端到后端
     *
     * @param inputPass 前端传到后端的密码
     * @return md5(str)
     */
    public static String inputPassToFromPass(String inputPass) {
        //为了防止盐被获取，这里直接筛选盐中部分内容进行加密处理
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * 二次加密，后端到数据库
     *
     * @param fromPass 从后端传到数据库的密码
     * @param salt     用于存储在数据库二次加密的盐
     * @return md5(str)
     */
    public static String fromPassToDBPass(String fromPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + fromPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass, String salt) {
        String fromPass = inputPassToFromPass(inputPass);
        String dbPass = fromPassToDBPass(fromPass, salt);
        return dbPass;
    }

    public static void main(String[] args) {
        //ca96df1c6cd528a3541f80a77900272b
        System.out.println(inputPassToFromPass("admin"));
        System.out.println(salt);
        System.out.println(fromPassToDBPass("ca96df1c6cd528a3541f80a77900272b", salt));
        System.out.println(inputPassToDBPass("12345678", salt));
    }
}
