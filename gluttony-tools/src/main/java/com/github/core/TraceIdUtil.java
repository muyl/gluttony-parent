package com.github.core;

/**
 * @author 拓仲 on 2020/3/28
 */
public class TraceIdUtil {
    public static String getTraceId() {
        return UUIDUtils.getUUID().toUpperCase();
    }
}
