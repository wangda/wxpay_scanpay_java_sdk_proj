/**
 * HTTPRequestException.java
 * 2016年6月12日
 * ©2015 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.common;

/**
 * https请求异常<p>
 * 网络异常，服务器异常，非200响应码 抛出此异常
 * 
 * @author wangda
 */
public class HttpsRequestException extends RuntimeException{
    private static final long serialVersionUID = -5952602824987647656L;

    public HttpsRequestException(String msg) {
        super(msg);
    }
    
    public HttpsRequestException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
    
    public HttpsRequestException(Throwable throwable) {
        super(throwable);
    }
}
