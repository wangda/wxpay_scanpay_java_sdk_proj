/**
 * TransfersService.java
 * 2016年5月19日
 * ©2015 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import com.tencent.common.Configure;
import com.tencent.common.Https;
import com.tencent.common.OkHttpsRequest;
import com.tencent.common.Util;
import com.tencent.protocol.mmpaymkttransfers.SendRedpackReqData;
import com.tencent.protocol.mmpaymkttransfers.SendRedpackRespData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * 企业转账服务类
 * 
 * @author wangda
 */
public class RedpackService{
    //API的地址
    private String apiURL = Configure.REDPACK_API;
    
    public RedpackService(){
    }
    
    public SendRedpackRespData request(SendRedpackReqData req){
        OkHttpsRequest httpRequest = new OkHttpsRequest();
        String content = "";
        try {
            content = httpRequest.sendPost(apiURL, req);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        
        SendRedpackRespData resp = (SendRedpackRespData) Util.getObjectFromXML(content, SendRedpackRespData.class, true);
        return resp;
    }
}
