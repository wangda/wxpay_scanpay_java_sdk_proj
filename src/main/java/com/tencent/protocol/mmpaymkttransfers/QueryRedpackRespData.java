/**
 * SendRedpackRespData.java
 * 2016年6月11日
 * ©2015 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.protocol.mmpaymkttransfers;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 查询企业发红包信息的响应对象
 * 
 * @author wangda
 */
@XStreamAlias("xml")
public class QueryRedpackRespData {
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

    /** 商户订单号 */
    @XStreamAlias("mch_billno")
    private String mchBillno = "";
    
    /** 商户号 */
    @XStreamAlias("mch_id")
    private String mchid = "";

    /** 红包单号:使用API发放现金红包时返回的红包单号 */
    @XStreamAlias("detail_id")
    private String detailId = "";

    /** 红包状态 > SENDING:发放中；SENT:已发放待领取；FAILED：发放失败；RECEIVED:已领取；REFUND:已退款 */
    @XStreamAlias("status")
    private String status = "";

    /** 发放类型 > API:通过API接口发放；UPLOAD:通过上传文件方式发放；ACTIVITY:通过活动方式发放 */
    @XStreamAlias("send_type")
    private String sendType = "";

    /** 红包类型 > GROUP:裂变红包 ；NORMAL:普通红包 */
    @XStreamAlias("hb_type")
    private String hbType = "";

    /** 红包个数 */
    @XStreamAlias("total_num")
    private int totalNum = 0;

    /** 红包金额 */
    @XStreamAlias("total_amount")
    private int totalAmount = 0;

    /** 失败原因 */
    @XStreamAlias("reason")
    private String reason = "";

    /** 红包发送时间 */
    @XStreamAlias("send_time")
    private String sendTime = "";

    /** 红包退款时间 */
    @XStreamAlias("refund_time")
    private String refundTime = "";

    /** 红包退款金额 */
    @XStreamAlias("refund_amount")
    private int refundAmount = 0;

    /** 祝福语 */
    @XStreamAlias("wishing")
    private String wishing = "";

    /** 活动描述 */
    @XStreamAlias("remark")
    private String remark = "";

    /** 活动名称 */
    @XStreamAlias("act_name")
    private String actName = "";

    /** 裂变红包领取列表 */
    @XStreamAlias("hblist")
    private String hblist = "";

    /** 领取红包的Openid */
    @XStreamAlias("openid")
    private String openid = "";

    /** 领取金额 */
    @XStreamAlias("amount")
    private int amount = 0;

    /** 领取红包的时间 */
    @XStreamAlias("rcv_time")
    private String rcvTime = "";
    
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
          
          .append("mch_billno:").append(mchBillno).append(";\r\n")
          .append("mch_id:").append(mchid).append(";\r\n")
          .append("detail_id:").append(detailId).append(";\r\n")
          .append("status:").append(status).append(";\r\n")
          .append("send_type:").append(sendType).append(";\r\n")
          .append("hb_type:").append(hbType).append(";\r\n")
          .append("total_num:").append(totalNum).append(";\r\n")
          .append("total_amount:").append(totalAmount).append(";\r\n")
          .append("reason:").append(reason).append(";\r\n")
          .append("send_time:").append(sendTime).append(";\r\n")
          .append("refund_time:").append(refundTime).append(";\r\n")
          .append("refund_amount:").append(refundAmount).append(";\r\n")
          .append("wishing:").append(wishing).append(";\r\n")
          .append("remark:").append(remark).append(";\r\n")
          .append("act_name:").append(actName).append(";\r\n")
          .append("hblist:").append(hblist).append(";\r\n")
          .append("openid:").append(openid).append(";\r\n")
          .append("amount:").append(amount).append(";\r\n")
          .append("rcv_time:").append(rcvTime).append(";\r\n")
          .append("]");
        
        return sb.toString();
    }

    public String getReturnCode() {
        return returnCode;
    }

    public QueryRedpackRespData setReturnCode(String returnCode) {
        this.returnCode = returnCode;
        return this;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public QueryRedpackRespData setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
        return this;
    }

    public String getResultCode() {
        return resultCode;
    }

    public QueryRedpackRespData setResultCode(String resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public String getErrCode() {
        return errCode;
    }

    public QueryRedpackRespData setErrCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public QueryRedpackRespData setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
        return this;
    }

    public String getMchBillno() {
        return mchBillno;
    }

    public QueryRedpackRespData setMchBillno(String mchBillno) {
        this.mchBillno = mchBillno;
        return this;
    }

    public String getMchid() {
        return mchid;
    }

    public QueryRedpackRespData setMchid(String mchid) {
        this.mchid = mchid;
        return this;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public QueryRedpackRespData setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getSendTime() {
        return sendTime;
    }

    public QueryRedpackRespData setSendTime(String sendTime) {
        this.sendTime = sendTime;
        return this;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getHbType() {
        return hbType;
    }

    public void setHbType(String hbType) {
        this.hbType = hbType;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public int getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(int refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getHblist() {
        return hblist;
    }

    public void setHblist(String hblist) {
        this.hblist = hblist;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRcvTime() {
        return rcvTime;
    }

    public void setRcvTime(String rcvTime) {
        this.rcvTime = rcvTime;
    }

}
