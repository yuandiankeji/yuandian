package com.yuandian.client.net;

import com.yuandian.client.handler.AbstractRespHandler;
import sun.net.www.protocol.file.FileURLConnection;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author twjitm 2019/4/15/23:24
 */
public class MessageRegister {
    private static ConcurrentHashMap<Integer, AbstractRespHandler> handlerMap = new ConcurrentHashMap<>();

    public static void register(String scanPath) {

        List<Class<?>> classList = null;
        try {

            classList = MessageRegister.scanRpcService(scanPath, ".class", AbstractRespHandler.class);

            for (Class clazz : classList) {
                try {
                    AbstractRespHandler handler = (AbstractRespHandler) clazz.newInstance();
                    handlerMap.put(handler.getCmd(), handler);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AbstractRespHandler getHandlerMap(int cmd) {
        return handlerMap.get(cmd);
    }


    public static String[] scanNamespaceFiles(String namespace, String fileext, boolean isReturnCanonicalPath, boolean checkSub) {
        String respath = namespace.replace('.', '/');
        respath = respath.replace('.', '/');

        List<String> tmpNameList = new ArrayList<>();
        try {
            URL url;
            if (!respath.startsWith("/"))
                url = MessageRegister.class.getResource("/" + respath);
            else
                url = MessageRegister.class.getResource(respath);

            URLConnection tmpURLConnection = url.openConnection();
            String tmpItemName;
            if (tmpURLConnection instanceof JarURLConnection) {
                JarURLConnection tmpJarURLConnection = (JarURLConnection) tmpURLConnection;
                int tmpPos;
                String tmpPath;
                JarFile jarFile = tmpJarURLConnection.getJarFile();
                Enumeration<JarEntry> entrys = jarFile.entries();
                while (entrys.hasMoreElements()) {
                    JarEntry tmpJarEntry = entrys.nextElement();
                    if (!tmpJarEntry.isDirectory()) {
                        tmpItemName = tmpJarEntry.getName();
                        if (tmpItemName.indexOf('$') < 0
                                && (fileext == null || tmpItemName.endsWith(fileext))) {
                            tmpPos = tmpItemName.lastIndexOf('/');
                            if (tmpPos > 0) {
                                tmpPath = tmpItemName.substring(0, tmpPos);
                                if (checkSub) {
                                    if (tmpPath.startsWith(respath)) {

                                        String r = tmpItemName.substring(respath.length() + 1).replaceAll("/", ".");
                                        tmpNameList.add(r);
                                    }
                                } else {
                                    if (respath.equals(tmpPath)) {
                                        tmpNameList.add(tmpItemName.substring(tmpPos + 1));
                                    }
                                }

                            }
                        }
                    }
                }
                jarFile.close();
            } else if (tmpURLConnection instanceof FileURLConnection) {
                File file = new File(url.getFile());
                if (file.exists() && file.isDirectory()) {
                    File[] fileArray = file.listFiles();
                    for (File f : fileArray) {
                        if (f.isDirectory() && f.getName().contains("."))
                            continue;

                        if (isReturnCanonicalPath) {
                            tmpItemName = f.getCanonicalPath();
                        } else {
                            tmpItemName = f.getName();
                        }
                        if (f.isDirectory()) {
                            String[] inner = scanNamespaceFiles(namespace + "." + tmpItemName, fileext, isReturnCanonicalPath);
                            if (inner == null) {
                                continue;
                            }
                            for (String i : inner) {
                                if (i != null) {
                                    tmpNameList.add(tmpItemName + "." + i);
                                }
                            }
                        } else if (fileext == null || tmpItemName.endsWith(fileext)) {
                            tmpNameList.add(tmpItemName);
                        } else {
                            continue;// 明确一下，不符合要求就跳过
                        }
                    }
                } else {

                }
            }
        } catch (Exception e) {
        }


        if (tmpNameList.size() > 0) {
            String[] r = new String[tmpNameList.size()];
            tmpNameList.toArray(r);
            tmpNameList.clear();
            return r;
        }
        return null;
    }

    public static String[] scanNamespaceFiles(String namespace, String fileext, boolean isReturnCanonicalPath) {
        return scanNamespaceFiles(namespace, fileext, isReturnCanonicalPath, false);
    }

    private static List<Class<?>> scanRpcService(String namespace, String ext, Class clazz) throws Exception {
        String[] fileNames = scanNamespaceFiles(namespace, ext, false, true);
        // 加载class,获取协议命令
        List<Class<?>> list = new ArrayList<>();
        if (fileNames != null) {
            for (String fileName : fileNames) {
                String realClass = namespace
                        + "."
                        + fileName.subSequence(0, fileName.length()
                        - (ext.length()));
                Class<?> messageClass = Class.forName(realClass);
                if (clazz.isAssignableFrom(messageClass)) {
                    System.out.println("scan message class :"+messageClass);
                    list.add(messageClass);
                }


            }
        }
        return list;
    }
}



