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
 * 企业发红包请求对象
 * 
 * @author wangda
 */
@XStreamAlias("xml")
public class SendRedpackReqData {
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
    @XStreamAlias("wxappid")
    private String appid = "";

    /** 红包发送者名称 */
    @XStreamAlias("send_name")
    private String sendName = "";

    /** 接受红包的用户 */
    @XStreamAlias("re_openid")
    private String reOpenid = "";

    /** 付款金额，单位分 */
    @XStreamAlias("total_amount")
    private int totalAmount = 0;

    /** 红包发放总人数 */
    @XStreamAlias("total_num")
    private int totalNum = 1;

    /** 红包祝福语 */
    @XStreamAlias("wishing")
    private String wishing = "";

    /** 调用接口的机器Ip地址 */
    @XStreamAlias("client_ip")
    private String clientIp = "";

    /** 活动名称 */
    @XStreamAlias("act_name")
    private String actName = "";

    /** 备注信息 */
    @XStreamAlias("remark")
    private String remark = "";

    /**
     * 
     * @param mchBillno 商户订单号（每个订单号必须唯一）
     * @param sendName 红包发送者名称
     * @param reOpenid 接受红包的用户
     * @param totalAmount 付款金额，单位分
     * @param totalNum 红包发放总人数
     * @param wishing 红包祝福语
     * @param clientIp 调用接口的机器Ip地址
     * @param actName 活动名称
     * @param remark 备注信息
     */
    public SendRedpackReqData(String mchBillno,
            String sendName, 
            String reOpenid, 
            int totalAmount, 
            int totalNum, 
            String wishing, 
            String clientIp,
            String actName, 
            String remark) {
        this.mchBillno = mchBillno;
        this.sendName = sendName;
        this.reOpenid = reOpenid;
        this.totalAmount = totalAmount;
        this.totalNum = totalNum;
        this.wishing = wishing;
        this.clientIp = clientIp;
        this.actName = actName;
        this.remark = remark;
        
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

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getReOpenid() {
        return reOpenid;
    }

    public void setReOpenid(String reOpenid) {
        this.reOpenid = reOpenid;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
