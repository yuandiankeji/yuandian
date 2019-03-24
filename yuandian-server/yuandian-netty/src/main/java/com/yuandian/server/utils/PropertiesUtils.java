package com.yuandian.server.utils;


import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author by twjitm on 2018/11/19/17:09
 */
public class PropertiesUtils {

    public static Properties load(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            throw new NullPointerException("");
        }
        Properties p = new Properties();
        try {
            InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath);
            p.load(new InputStreamReader(inputStream));
            return p;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
