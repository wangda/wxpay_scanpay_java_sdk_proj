/**
 * TransfersReqData.java
 * 2016年5月19日
 * ©2015 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.protocol.mmpaymkttransfers;

import com.tencent.common.Configure;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Signature;
import com.tencent.common.Util;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * 企业转账请求数据
 * 
 * @author wangda
 */
@XStreamAlias("xml")
public class TransfersReqData {

    @XStreamAlias("mch_appid")
    private String appid;
    
    @XStreamAlias("mchid")
    private String mchid;
    
    @XStreamAlias("device_info")
    private String deviceInfo;
    
    @XStreamAlias("nonce_str")
    private String nonceStr;
    
    @XStreamAlias("sign")
    private String sign;
    
    @XStreamAlias("partner_trade_no")
    private String partnerTradeNo;
    
    @XStreamAlias("openid")
    private String openid;
    
    @XStreamAlias("check_name")
    private String checkName;
    
    @XStreamAlias("re_user_name")
    private String reUserName;
    
    @XStreamAlias("amount")
    private int amount;
    
    @XStreamAlias("desc")
    private String desc;
    
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;
    
    
    /**
     * 
     * @param deviceInfo
     * @param openid
     * @param checkName
     * @param reUserName
     * @param amount
     * @param desc
     * @param spBillCreateIP
     */
    public TransfersReqData(String deviceInfo, 
            String partnerTradeNo,
            String openid, 
            String checkName, 
            String reUserName, 
            int amount, 
            String desc, 
            String spBillCreateIP){
        setOpenid(openid);
        setCheckName(checkName);
        setAmount(amount);
        setDesc(desc);
        setPartnerTradeNo(partnerTradeNo);
        
        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(Configure.getAppid());

        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMchid(Configure.getMchid());

        //商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
        setDeviceInfo(deviceInfo);

        //订单生成的机器IP
        setSpbillCreateIp(spBillCreateIP);

        //随机字符串，不长于32 位
        setNonceStr(RandomStringGenerator.getRandomStringByLength(32));

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

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
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

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getReUserName() {
        return reUserName;
    }

    public void setReUserName(String reUserName) {
        this.reUserName = reUserName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }
    
    
    public static void main(String[] args) {
        TransfersReqData req = new TransfersReqData("deviceInfo", "partner_trade_no", "openId", 
                "CheckName", "reUserName", 100, "desc", "spBilIp");
        
        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        xstream.processAnnotations(TransfersReqData.class);
        String xml = xstream.toXML(req);
        System.out.println(xml);
    }
    
    
}
