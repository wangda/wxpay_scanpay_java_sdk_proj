/**
 * HttpClientCache.java
 * 2016年7月28日
 * ©2015-2016 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.common.httpclientcache;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.HttpClients;

import com.tencent.common.Configure;
import com.tencent.common.PayAccount;

/**
 * 使用HttpClient进行支付的Http连接连接池<p>
 * 该池子中保存了所有微信支付业主的Http连接对象。可以直接使用这个连接对象发送HTTP请求，而不用每次都重新实例化
 * 
 * @author wangda
 */
public class HttpClientCache {
    private static Map<String, HttpClient> clientMap = new HashMap<>(32);
    
    /**
     * 根据支付账号，返回该支付账户的连接
     * @param account
     * @return
     * @throws Exception
     */
    public static HttpClient getClient(PayAccount account) throws Exception {
        String key = account.getMchId() + "_" + account.getSubMchId();
        HttpClient client = clientMap.get(key);
        if (client != null) {
            return client;
        }
        
        InputStream instream = null;
        if (account.getCert() != null) {
            instream = account.getCertInputStream();
        } else {
            instream = new FileInputStream(new File(account.getCertLocalPath()));//加载本地的证书进行https加密传输
        }
        
        // 重新生成一个client
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try {
            keyStore.load(instream, account.getCertPassword().toCharArray());//设置证书密码
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, account.getCertPassword().toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        client = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        clientMap.put(key, client);
        
        return client;
    }
}