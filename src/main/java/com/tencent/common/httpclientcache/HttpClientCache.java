/**
 * HttpClientCache.java
 * 2016年7月28日
 * ©2015-2016 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.common.httpclientcache;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;

import com.tencent.common.PayAccount;
import com.tencent.common.Util;

/**
 * 使用HttpClient进行支付的Http连接连接池<p>
 * 该池子中保存了所有微信支付业主的Http连接对象。可以直接使用这个连接对象发送HTTP请求，而不用每次都重新实例化
 * 
 * @author wangda
 */
public class HttpClientCache {
//    private static Map<String, HttpClient> clientMap = new HashMap<>(32);
    private static Map<String, KeyStore> clientMap = new HashMap<>(32);
    
    /**
     * 根据支付账号，返回该支付账户的连接
     * @param account
     * @return
     * @throws Exception
     */
    public static HttpClient getClient(PayAccount account) throws Exception {
        String key = account.getMchId() + "_" + account.getSubMchId();
//        HttpClient client = clientMap.get(key);
        KeyStore keystore = clientMap.get(key);
        if (keystore != null) {
            InputStream instream = null;
            if (account.getCert() != null) {
                instream = account.getCertInputStream();
            } else {
                instream = new FileInputStream(new File(account.getCertLocalPath()));//加载本地的证书进行https加密传输
            }
            
            // 重新生成一个client
            keystore = KeyStore.getInstance("PKCS12");
            try {
                keystore.load(instream, account.getCertPassword().toCharArray());//设置证书密码
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } finally {
                instream.close();
            }
            
            clientMap.put(key, keystore);
        }
        
        return buildHttpClient(keystore, account);
    }
    
    static HttpClient buildHttpClient(KeyStore keystore, PayAccount account){
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keystore, account.getCertPassword().toCharArray())
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .build();
        } catch (KeyManagementException | UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
            Util.log(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }

        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                // SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        HttpClient client = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        return client;
    }
}
