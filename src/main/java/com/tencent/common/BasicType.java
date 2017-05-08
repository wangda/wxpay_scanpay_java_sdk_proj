/**
 * BasicType.java
 * 1.0
 * 2015年9月9日 下午11:42:25
 * Copyright @ 2014 王答
 */
package com.tencent.common;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 基本类型，包括基本的值类型，包装类型，日期类型
 * 
 * @author wangda
 */
public class BasicType {
    private static Set typeSet = new HashSet();
    
    static {
        // 值类型
        typeSet.add(byte.class);
        typeSet.add(short.class);
        typeSet.add(int.class);
        typeSet.add(long.class);
        typeSet.add(float.class);
        typeSet.add(double.class);
        typeSet.add(boolean.class);
        typeSet.add(char.class);
        
        typeSet.add(byte[].class);
        
        // 包装类型
        typeSet.add(Byte.class);
        typeSet.add(Short.class);
        typeSet.add(Integer.class);
        typeSet.add(Long.class);
        typeSet.add(Float.class);
        typeSet.add(Double.class);
        typeSet.add(Boolean.class);
        typeSet.add(Character.class);
        typeSet.add(String.class);
        typeSet.add(BigInteger.class);
        typeSet.add(BigDecimal.class);
        typeSet.add(Number.class);
        
        // 日期类型
        typeSet.add(Date.class);
        typeSet.add(java.sql.Date.class);
        typeSet.add(Timestamp.class);
        typeSet.add(Calendar.class);
    }
    
    /**
     * 检查一个Class是否是基本类型
     * @param clz
     * @return
     */
    public static boolean isBasicType(Class clz) {
        return typeSet.contains(clz);
    }
    
    /**
     * 检查一个Type是否是基本类型
     * @param type
     * @return
     */
    public static boolean isBasicType(Type type) {
        return typeSet.contains(type.getClass());
    }
    
    /**
     * 检查一个对象是否是基本类型
     * @param obj
     * @return
     */
    public static boolean isBasicType(Object obj) {
        if (obj == null)
            return false;
        
        return typeSet.contains(obj.getClass());
    }
}
