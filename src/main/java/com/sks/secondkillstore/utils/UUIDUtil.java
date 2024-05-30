package com.sks.secondkillstore.utils;

import java.util.UUID;

/**
 * @Author HQD
 * @Date 2024/4/15 8:34
 * @Version 1.0
 */
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
