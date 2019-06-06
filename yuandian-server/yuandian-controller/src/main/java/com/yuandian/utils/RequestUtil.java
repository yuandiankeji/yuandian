package com.yuandian.utils;


import com.yuandian.core.common.Constants;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal();
    public static long getCurrentUid() {
        HttpServletRequest request = threadLocal.get();
        return Long.valueOf(request.getHeader(Constants.UID));
    }

    public static void setRequest(HttpServletRequest request) {
        threadLocal.set(request);
    }
}
