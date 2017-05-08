/**
 * PayAccount.java
 * 2016年7月28日
 * ©2015-2016 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 支付账号
 * @author wangda
 */
public class PayAccount {
    //微信分配的公众号ID（开通公众号之后可以获取到）
    private String appId = "";
    
    // 安全密钥
    private String key = "";

    //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
    private String mchId = "";

    //受理模式下给子商户分配的子商户号
    private String subMchId = "";

    //HTTPS证书的本地路径
    private String certLocalPath = "";
    
    private byte[] cert = null;

    //HTTPS证书密码，默认密码等于商户号MCHID
    private String certPassword = "";
    
    private InputStream certStream = null;
    
    public InputStream getCertInputStream() {
        if (cert == null) {
            throw new IllegalArgumentException("证书字节码流为空");
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(cert);
        return bis;
    }
    

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCertLocalPath() {
        return certLocalPath;
    }

    public void setCertLocalPath(String certLocalPath) {
        this.certLocalPath = certLocalPath;
    }

    public byte[] getCert() {
        return cert;
    }

    public void setCert(byte[] cert) {
        this.cert = cert;
    }

    public String getCertPassword() {
        return certPassword;
    }

    public void setCertPassword(String certPassword) {
        this.certPassword = certPassword;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }
    
}
