package com.tencent.service;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.tencent.common.Configure;
import com.tencent.protocol.mmpaymkttransfers.QueryRedpackReqData;
import com.tencent.protocol.mmpaymkttransfers.QueryRedpackRespData;

public class QueryRedpackServiceTest {

    //@Test
    public void testRequest() {
        Configure.setAppID("wxa01703df65b96e65");
        Configure.setMchID("1285030101");
        Configure.setCertLocalPath("D:\\@work\\doc\\weixin-cert\\apiclient_cert.p12");
        Configure.setCertPassword("1285030101");
        Configure.setKey("2trawe0weixin1pay53fea9c810e3749");
        Configure.setIp("192.168.3.114");
        QueryRedpackService svr = new QueryRedpackService();
        
        QueryRedpackReqData req = new QueryRedpackReqData("20160611155901");
        
        QueryRedpackRespData resp = svr.request(req);
        
        resp = svr.request(req);
        
        System.out.println(resp.toString());
        
        fail("Not yet implemented");
    }

}
