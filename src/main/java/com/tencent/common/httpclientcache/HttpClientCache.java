/**
 * HttpClientCache.java
 * 2016年7月28日
 * ©2015-2016 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.common.httpclientcache;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClients;

import com.tencent.common.PayAccount;
import com.tencent.common.Util;

import cn.trawe.tencent.contst.ValidCertSwitch;


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

        HttpClient client = null;
        Util.log("使用新的client");
/*        HttpClient client = clientMap.get(key);
        if (client != null) {
            return client;
        }*/

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
    
    static HttpClient buildHttpClient(KeyStore keystore, PayAccount account) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException{
        try {
            
        } catch (Exception e) {
        }
        SSLContext sslcontext = null;
        SSLConnectionSocketFactory sslsf = null;
        if(ValidCertSwitch.isValidCert){
            //访问微信
            // Trust own CA and all self-signed certs
            sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keystore, account.getCertPassword().toCharArray())
                    .build();
            // Allow TLSv1 protocol only
            sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        } else {
            //访问模拟器
            // Trust own CA and all self-signed certs
            sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(null, new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            // TODO Auto-generated method stub
                            return true;
                        }
                    })
                    .loadKeyMaterial(keystore, account.getCertPassword().toCharArray())
                    .build();
            // Allow TLSv1 protocol only
            sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    new X509HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                        @Override
                        public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                        }
                        @Override
                        public void verify(String host, X509Certificate cert) throws SSLException {
                        }
                        @Override
                        public void verify(String host, SSLSocket ssl) throws IOException {
                        }
                    });
        }

        HttpClient client = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();

        return client;
    }
}
