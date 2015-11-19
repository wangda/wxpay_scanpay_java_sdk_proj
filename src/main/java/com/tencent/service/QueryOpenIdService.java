package com.tencent.service;

import com.tencent.common.*;
import com.tencent.protocol.pay_protocol.ScanPayReqData;
import com.tencent.protocol.tool_protocol.QueryOpenIdReqData;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

/**
 * User: wangda
 * Date: 2015/11/19
 * Time: 15:15
 */
public class QueryOpenIdService extends BaseService{

    public QueryOpenIdService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.QUERY_OPENID);
    }

    /**
     * 请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(QueryOpenIdReqData queryReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(queryReqData);

        return responseString;
    }
}
