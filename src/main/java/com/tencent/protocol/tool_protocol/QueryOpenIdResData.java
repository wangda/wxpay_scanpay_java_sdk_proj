/**
 * QueryOpenIdResData.java
 * 2015年11月19日
 * ©2015 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.protocol.tool_protocol;

/**
 * 查询openId响应数据
 * @author wangda
 */
public class QueryOpenIdResData {

    /** 返回状态码 */
    private String return_code;

    /** 返回信息 */
    private String return_msg;

    /********** 以下字段在return_code为SUCCESS的时候有返回 **********/
    /** 公众账号ID */
    private String appid;

    /** 商户号 */
    private String mch_id;

    /** 随机字符串 */
    private String nonce_str;

    /** 签名 */
    private String sign;

    /** 业务结果 */
    private String result_code;

    /** 错误代码 */
    private String err_code;

    /** 用户标识:return_code 和result_code都为SUCCESS的时候有返回 */
    private String openid;
    
    /** 获取openId是否成功 */
    public boolean isSuccess() {
        return "SUCCESS".equals(return_code) && "SUCCESS".equals(result_code);
    }
    
    /** 是否是系统错误 */
    public boolean isSystemError() {
        return "SYSTEMERROR".equals(err_code);
    }

    /** 是否是授权码过期  */
    public boolean isExpire() {
        return "AUTHCODEEXPIRE".equals(err_code);
    }

    /** 是否授权码错误 */
    public boolean isCodeError() {
        return "AUTH_CODE_ERROR".equals(err_code);
    }

    /** 是否授权码检验错误 */
    public boolean isCodeInvalid() {
        return "AUTH_CODE_INVALID".equals(err_code);
    }
    
    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
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

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
    
    
}
