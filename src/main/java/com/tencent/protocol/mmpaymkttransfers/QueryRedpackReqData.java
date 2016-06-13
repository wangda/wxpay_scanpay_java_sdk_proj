/**
 * SendRedpackReqData.java
 * 2016年6月11日
 * ©2015 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.protocol.mmpaymkttransfers;

import com.tencent.common.Configure;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Signature;
import com.tencent.common.Util;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 查询企业发红包信息的请求对象
 * 
 * @author wangda
 */
@XStreamAlias("xml")
public class QueryRedpackReqData {
    /** 随机字符串，不长于32位 */
    @XStreamAlias("nonce_str")
    private String nonceStr = "";

    /** 签名 */
    @XStreamAlias("sign")
    private String sign = "";

    /** 商户订单号（每个订单号必须唯一），接口根据商户订单号支持重入，如出现超时可再调用。 */
    @XStreamAlias("mch_billno")
    private String mchBillno = "";

    /** 微信支付分配的商户号 */
    @XStreamAlias("mch_id")
    private String mchid = "";

    /** 微信分配的公众账号ID */
    @XStreamAlias("appid")
    private String appid = "";

    /** MCHT:通过商户订单号获取红包信息 */
    @XStreamAlias("bill_type")
    private String billType = "MCHT";

    /**
     * 构造函数
     * @param mchBillno 商户订单号（每个订单号必须唯一）
     */
    public QueryRedpackReqData(String mchBillno) {
        this.mchBillno = mchBillno;
        
        this.nonceStr = RandomStringGenerator.getRandomStringByLength(32);
        this.mchid = Configure.getMchid();
        this.appid = Configure.getAppid();
        String sign = Signature.getSign(Util.toMap(this));
        this.sign = sign;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMchBillno() {
        return mchBillno;
    }

    public void setMchBillno(String mchBillno) {
        this.mchBillno = mchBillno;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

}
