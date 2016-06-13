package com.tencent.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tencent.common.Configure;
import com.tencent.common.Util;
import com.tencent.protocol.mmpaymkttransfers.SendRedpackReqData;
import com.tencent.protocol.mmpaymkttransfers.SendRedpackRespData;

public class RedpackServiceTest {

    @Test
    public void testRequest() {
        Configure.setAppID("");
        Configure.setMchID("");
        Configure.setCertLocalPath("D:\\@work\\doc\\weixin-cert\\apiclient_cert.p12");
        Configure.setCertPassword("");
        Configure.setKey("2trawe0weixin1pay53fea9c810e3749");
        Configure.setIp("192.168.3.114");
        RedpackService svr = new RedpackService();
        
        SendRedpackReqData req = new SendRedpackReqData("20160611155901", "高速纵横", "oI2FEuEKwafKrpiHS-gQQwzXBUVQ", 
                100, 1, "一路平安", "192.168.3.114", "测试活动名称", "备注");
        
        SendRedpackRespData resp = svr.request(req);
        
        System.out.println(resp.toString());
        
        fail("Not yet implemented");
    }

}
