/**
 * SendRedpackRespData.java
 * 2016年6月11日
 * ©2015 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.protocol.mmpaymkttransfers;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 企业发红包响应对象
 * 
 * @author wangda
 */
@XStreamAlias("xml")
public class SendRedpackRespData {
    // 协议层
    @XStreamAlias("return_code")
    private String returnCode = "";
    
    @XStreamAlias("return_msg")
    private String returnMsg = "";

    // 协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
    @XStreamAlias("result_code")
    private String resultCode = "";
    
    @XStreamAlias("err_code")
    private String errCode = "";
    
    @XStreamAlias("err_code_des")
    private String errCodeDes = "";
    
    // 以下字段在return_code和result_code都为SUCCESS的时候有返回
    @XStreamAlias("mch_billno")
    private String mchBillno = "";
    
    @XStreamAlias("mch_id")
    private String mchid = "";
    
    @XStreamAlias("wxappid")
    private String appid = "";

    @XStreamAlias("re_openid")
    private String reOpenid = "";

    @XStreamAlias("total_amount")
    private int totalAmount = 0;

    @XStreamAlias("send_time")
    private String sendTime = "";

    @XStreamAlias("send_listid")
    private String sendListid = "";
    
    /**
     * 请求是否处理成功
     * @return
     */
    public boolean isSuccess() {
        return "SUCCESS".equals(returnCode) || "SUCCESS".equals(resultCode);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SendRedpackRespData[\r\n")
          .append("return_code:").append(returnCode).append(";\r\n")
          .append("return_msg:").append(returnMsg).append(";\r\n")
          
          .append("result_code:").append(resultCode).append(";\r\n")
          .append("err_code:").append(errCode).append(";\r\n")
          .append("err_code_des:").append(errCodeDes).append(";\r\n")
          
          .append("mch_billno:").append(returnCode).append(";\r\n")
          .append("mch_id:").append(mchid).append(";\r\n")
          .append("wxappid:").append(appid).append(";\r\n")
          .append("re_openid:").append(reOpenid).append(";\r\n")
          .append("total_amount:").append(totalAmount).append(";\r\n")
          .append("send_time:").append(sendTime).append(";\r\n")
          .append("send_listid:").append(sendListid).append(";\r\n")
          .append("]");
        
        return sb.toString();
    }

    public String getReturnCode() {
        return returnCode;
    }

    public SendRedpackRespData setReturnCode(String returnCode) {
        this.returnCode = returnCode;
        return this;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public SendRedpackRespData setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
        return this;
    }

    public String getResultCode() {
        return resultCode;
    }

    public SendRedpackRespData setResultCode(String resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public String getErrCode() {
        return errCode;
    }

    public SendRedpackRespData setErrCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public SendRedpackRespData setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
        return this;
    }

    public String getMchBillno() {
        return mchBillno;
    }

    public SendRedpackRespData setMchBillno(String mchBillno) {
        this.mchBillno = mchBillno;
        return this;
    }

    public String getMchid() {
        return mchid;
    }

    public SendRedpackRespData setMchid(String mchid) {
        this.mchid = mchid;
        return this;
    }

    public String getAppid() {
        return appid;
    }

    public SendRedpackRespData setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getReOpenid() {
        return reOpenid;
    }

    public SendRedpackRespData setReOpenid(String reOpenid) {
        this.reOpenid = reOpenid;
        return this;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public SendRedpackRespData setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getSendTime() {
        return sendTime;
    }

    public SendRedpackRespData setSendTime(String sendTime) {
        this.sendTime = sendTime;
        return this;
    }

    public String getSendListid() {
        return sendListid;
    }

    public SendRedpackRespData setSendListid(String sendListid) {
        this.sendListid = sendListid;
        return this;
    }
    
}
