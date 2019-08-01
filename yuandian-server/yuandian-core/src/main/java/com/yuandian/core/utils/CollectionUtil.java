package com.yuandian.core.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author twjitm
 */
public class CollectionUtil {

    /**
     * 判断某个list是否没有数据
     */
    public static <T> boolean isEmpty(List<T> list) {
        boolean b = false;
        if (list == null || list.isEmpty()) {
            b = true;
        }
        return b;
    }


    /**
     * 判断某个list是否没有数据
     */
    public static <T> boolean isEmpty(Set<T> list) {
        boolean b = false;
        if (list == null || list.isEmpty()) {
            b = true;
        }
        return b;
    }


    public static <T> boolean isEmpty(T[] list) {
        boolean b = false;
        if (list == null || list.length == 0) {
            b = true;
        }
        return b;
    }

    /**
     * 判断一个map是否为空
     */
    public static boolean isEmpty(Map<Object, Object> map) {
        boolean b = false;
        if (map == null || map.isEmpty()) {
            b = true;
        }
        return b;
    }


}
