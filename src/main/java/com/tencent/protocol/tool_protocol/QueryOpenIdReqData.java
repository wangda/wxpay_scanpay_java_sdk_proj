
package com.tencent.protocol.tool_protocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.tencent.common.Configure;
import com.tencent.common.PayAccount;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Signature;
import com.tencent.common.Util;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 授权码查询OPENID接口
 * @author wangda
 */
public class QueryOpenIdReqData {
    /** 公众号ID */
    private String appid;
    /** 商户号 */
    private String mch_id;
    /** 授权码 */
    private String auth_code;
    /** 随机字符串 */
    private String nonce_str;
    /** 签名 */
    private String sign;

    private PayAccount account;
    
    public QueryOpenIdReqData(String authCode) {
        // 授权码
        setAuth_code(authCode);
        
        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(Configure.getAppid());

        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(Configure.getMchid());

        //随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));

        //根据API给的签名规则进行签名
        String sign = Signature.getSign(Util.toMap(this));
        setSign(sign);//把签名数据设置到Sign这个属性中
    }
    
    public QueryOpenIdReqData(PayAccount account, String authCode) {
        this.setAccount(account);
        // 授权码
        setAuth_code(authCode);
        
        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(account.getAppId());

        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(account.getMchId());

        //随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));

        //根据API给的签名规则进行签名
        String sign = Signature.getSign(Util.toMap(this));
        setSign(sign);//把签名数据设置到Sign这个属性中
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }

    public PayAccount getAccount() {
        return account;
    }

    public void setAccount(PayAccount account) {
        this.account = account;
    }
}
