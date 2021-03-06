package com.tencent.common;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: rizenguo
 * Date: 2014/10/23
 * Time: 14:59
 */
public class Util {

    //打log用
    private static Log logger = new Log(LoggerFactory.getLogger(Util.class));

    /**
     * 通过反射的方式遍历对象的属性和属性值，方便调试
     *
     * @param o 要遍历的对象
     * @throws Exception
     */
    public static void reflect(Object o) throws Exception {
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);
            Util.log(f.getName() + " -> " + f.get(o));
        }
    }

    public static byte[] readInput(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        out.close();
        in.close();
        return out.toByteArray();
    }

    public static String inputStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }


    public static InputStream getStringStream(String sInputString) throws UnsupportedEncodingException {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes("UTF-8"));
        }
        return tInputStringStream;
    }

    public static Object getObjectFromXML(String xml, Class tClass) {
        if (xml == null || "".equals(xml)) {
            return null;
        }
        return getObjectFromXML(xml, tClass, false);
    }
    
    public static Object getObjectFromXML(String xml, Class tClass, boolean useAlias) {
        //将从API返回的XML数据映射到Java对象
        XStream xStreamForResponseData = new XStream();
        if (useAlias) {
            xStreamForResponseData.processAnnotations(tClass);
        }
        xStreamForResponseData.alias("xml", tClass);
        xStreamForResponseData.ignoreUnknownElements();//暂时忽略掉一些新增的字段
        return xStreamForResponseData.fromXML(xml);
    }

    public static String getStringFromMap(Map<String, Object> map, String key, String defaultValue) {
        if (key == null || "".equals(key)) {
            return defaultValue;
        }
        String result = (String) map.get(key);
        if (result == null) {
            return defaultValue;
        } else {
            return result;
        }
    }

    public static int getIntFromMap(Map<String, Object> map, String key) {
        if ("".equals(key) || key == null) {
            return 0;
        }
        if (map.get(key) == null) {
            return 0;
        }
        return Integer.parseInt((String) map.get(key));
    }

    /**
     * 打log接口
     * @param log 要打印的log字符串
     * @return 返回log
     */
    public static String log(Object log){
        logger.i(log.toString());
        //System.out.println(log);
        return log.toString();
    }
    
    /**
     * 打印异常
     * @param ex
     */
    public static void log(String errMsg, Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println(errMsg);
        ex.printStackTrace(pw);
        log(pw.toString());
    }

    /**
     * 读取本地的xml数据，一般用来自测用
     * @param localPath 本地xml文件路径
     * @return 读到的xml字符串
     */
    public static String getLocalXMLString(String localPath) throws IOException {
        return Util.inputStreamToString(Util.class.getResourceAsStream(localPath));
    }
    
    /**
     * 将一个对象的属性转到一个Map对象中
     * @param obj
     * @return
     */
    public static Map<String, Object> toMap(Object obj) {
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object o;
            try {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                o = field.get(obj);
                if (o == null) {
                    continue;
                }
                
                // 只有基本数据类型才转到Map中
                if (!BasicType.isBasicType(o.getClass())) {
                    continue;
                }
                
                XStreamAlias alias = field.getDeclaredAnnotation(XStreamAlias.class);
                if (alias == null) {
                    map.put(field.getName(), o);
                } else {
                    map.put(alias.value(), o);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}

