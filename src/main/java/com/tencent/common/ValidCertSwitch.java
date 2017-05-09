/**
 * ValidCertSwitch.java
 * 2017年5月9日
 * ©2015-2016 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.common;

/**
 * 控制微信sdk是否检查证书
 * @author zhaoyouqiang
 */
public class ValidCertSwitch {

    private static boolean isValidCert = true;

    public static boolean isValidCert() {
        return isValidCert;
    }

    public static void setIsValidCert(boolean isValidCert) {
        ValidCertSwitch.isValidCert = isValidCert;
    }
}
