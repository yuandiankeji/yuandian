package com.yuandian.core.utils;


/**
 * @author twjitm
 */
public class ZStringUtil {

    /**
     * 默认的文本分隔符
     */
    public static final String Default_Split = "#";

    /**
     * 判断字符串是否为空
     *
     * @param string
     */
    public static boolean isEmptyStr(String string) {
        if (string == null || "".equals(string.trim())) {
            return true;
        }
        return false;
    }
}
