/**
 * OkHttpsRequest.java
 * 2016年6月13日
 * ©2015 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.common;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import com.tencent.service.IServiceRequest;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * 使用OkHttp组件进行Https请求
 * 
 * @author wangda
 */
public class OkHttpsRequest implements IServiceRequest{

    @Override
    public String sendPost(String apiURL, Object xmlObj) throws UnrecoverableKeyException, KeyManagementException,
            NoSuchAlgorithmException, KeyStoreException, IOException {
        return sendPost(apiURL, xmlObj, false);
    }

    @Override
    public String sendPost(String apiURL, Object xmlObj, boolean useAlias) throws UnrecoverableKeyException,
            KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
        //解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        xStreamForRequestPostData.processAnnotations(xmlObj.getClass());
        PayAccount account = getPayAccountFromObj(xmlObj);
        //将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xStreamForRequestPostData.toXML(xmlObj);
        String content = Https.instance(account).post(apiURL, postDataXML, account);
        return content;
    }
    
    private PayAccount getPayAccountFromObj(Object obj) {
        Field[] fieldList = obj.getClass().getDeclaredFields();
        for (Field f: fieldList) {
            if (f.getType() == PayAccount.class) {
                try {
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    return (PayAccount) f.get(obj);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        
        return null;
    }

}
