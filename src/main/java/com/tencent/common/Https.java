/**
 * Https.java
 * 2016年6月12日
 * ©2015 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * HTTPS请求工具类
 * @author wangda
 */
public class Https {
    private Logger logger = LoggerFactory.getLogger(Https.class);
    private int connectTimeout = 10; // 连接超时 10秒
    private int sendTimeout = 10;    // 发送数据超时 10 秒
    private int readTimeout = 20;    // 接收数据超时 20 秒
    private static Https https;
    private OkHttpClient client;
    public static Https instance() {
        if (https != null) {
            return https;
        }
        
        synchronized(Https.class) {
            if (https != null) {
                return https;
            }
            
            Https temp = new Https();
            temp.init();
            https = temp;
        }
        
        return https;
    }
    
    private synchronized void init() {
        try {
            // 加载本地的证书进行https加密传输
            FileInputStream certStream = new FileInputStream(new File(Configure.getCertLocalPath()));
            
            SSLSocketFactory sslSocketFactory = HttpsUtils.getSslSocketFactory(null, certStream, Configure.getCertPassword());
            client = new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory)
                    .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                    .readTimeout(readTimeout, TimeUnit.SECONDS)
                    .writeTimeout(sendTimeout, TimeUnit.SECONDS)
                    .build();
        } catch (Exception ex) {
            logger.error("初始化Https对象出错:" + ex.getMessage(), ex);
        }
    }
    
    public String post(String url, String content) {
        if (client == null) {
            init();
        }
        
        long start = System.currentTimeMillis();
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(okhttp3.MediaType.parse("text/*"), content))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new HttpsRequestException("请求[" + url+"]出错", e);
        }
        logger.info("Https请求耗时：" + (System.currentTimeMillis() - start) + "ms");
        
        if (response == null) {
            throw new HttpsRequestException("Https请求返回响应对象为空");
        }
        
        if (!response.isSuccessful()) {
            throw new HttpsRequestException("Https请求返回的状态码为：" + response.code());
        }
        try {
            return response.body().string();
        } catch (IOException e) {
            throw new HttpsRequestException("Https请求获取响应Body出错", e);
        }
    }
    
    public static void main(String[] args) {
        String postXML = "<xml>" + 
                "<appid></appid>" + 
                "<attach>订单额外描述</attach>" + 
                "<auth_code>120269300684844649</auth_code>" + 
                "<body>刷卡支付测试</body>" + 
                "<device_info>1000</device_info>" + 
                "<goods_tag></goods_tag>" + 
                "<mch_id>10000100</mch_id>" + 
                "<nonce_str>8aaee146b1dee7cec9100add9b96cbe2</nonce_str>" + 
                "<out_trade_no>1415757673</out_trade_no>" + 
                "<spbill_create_ip>14.17.22.52</spbill_create_ip>" + 
                "<time_expire></time_expire>" + 
                "<total_fee>1</total_fee>" + 
                "<sign>C29DB7DB1FD4136B84AE35604756362C</sign>" + 
                "</xml>";
        
        Configure.setCertLocalPath("D:\\data\\test-cert\\apiclient_cert.p12");
        Configure.setCertPassword("");
        String s = Https.instance().post("https://api.mch.weixin.qq.com/pay/micropay", postXML);
        s = Https.instance().post("https://api.mch.weixin.qq.com/pay/micropay", postXML);
        System.out.println(s);
    }
}
