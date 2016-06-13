/**
 * TransfersRespData.java
 * 2016年5月19日
 * ©2015 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.protocol.mmpaymkttransfers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * 企业转账响应数据
 * 
 * @author wangda
 */
@XStreamAlias("xml")
public class TransfersRespData {
    //协议层
    @XStreamAlias("return_code")
    private String returnCode = "";
    
    @XStreamAlias("return_msg")
    private String returnMsg = "";

    //协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
    @XStreamAlias("mch_appid")
    private String appid = "";
    
    @XStreamAlias("mchid")
    private String mchid = "";
    
    @XStreamAlias("device_info")
    private String deviceInfo = "";
    
    @XStreamAlias("nonce_str")
    private String nonceStr = "";
    
    @XStreamAlias("result_code")
    private String resultCode = "";
    
    @XStreamAlias("err_code")
    private String errCode = "";
    
    @XStreamAlias("err_code_des")
    private String errCodeDes = "";

    // 以下字段在return_code 和result_code都为SUCCESS的时候有返回
    @XStreamAlias("partner_trade_no")
    private String partnerTradeNo = ""; // 商户订单号

    @XStreamAlias("payment_no")
    private String paymentNo = "";  // 微信订单号

    @XStreamAlias("payment_time")
    private String paymentTime = ""; // 微信支付成功时间
    
    /**
     * 请求是否处理成功
     * @return
     */
    public boolean isSuccess() {
        return "SUCCESS".equals(returnCode) || "SUCCESS".equals(resultCode);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TransfersRespData[")
          .append("return_code:").append(returnCode).append(";\r\n")
          .append("return_msg:").append(returnMsg).append(";\r\n")
          
          .append("mch_appid:").append(returnCode).append(";\r\n")
          .append("mchid:").append(mchid).append(";\r\n")
          .append("device_info:").append(deviceInfo).append(";\r\n")
          .append("nonce_str:").append(nonceStr).append(";\r\n")
          .append("result_code:").append(resultCode).append(";\r\n")
          .append("err_code:").append(errCode).append(";\r\n")
          .append("err_code_des:").append(errCodeDes).append(";\r\n")
          .append("partner_trade_no:").append(partnerTradeNo).append(";\r\n")
          .append("payment_no:").append(paymentNo).append(";\r\n")
          .append("payment_time:").append(paymentTime).append(";\r\n")
          .append("]");
        
        return sb.toString();
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
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

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }
    
    public static void main(String[] args) {
        String xml = "<xml>" +
                    "<return_code><![CDATA[SUCCESS]]></return_code>" +
                    "<return_msg><![CDATA[return_msg]]></return_msg>" +
                    "<mch_appid><![CDATA[wxec38b8ff840bd989]]></mch_appid>" +
                    "<mchid><![CDATA[10013274]]></mchid>" +
                    "<device_info><![CDATA[deviceInfo]]></device_info>" +
                    "<nonce_str><![CDATA[lxuDzMnRjpcXzxLx0q]]></nonce_str>" +
                    "<result_code><![CDATA[SUCCESS]]></result_code>" +
                    "<partner_trade_no><![CDATA[10013574201505191526582441]]></partner_trade_no>" +
                    "<payment_no><![CDATA[1000018301201505190181489473]]></payment_no>" +
                    "<payment_time><![CDATA[2015-05-19 15:26:59]]></payment_time>" +
                    "</xml>";
        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        xstream.processAnnotations(TransfersRespData.class);
        TransfersRespData resp = (TransfersRespData) xstream.fromXML(xml);
        System.out.println(resp.toString());
    }
}
