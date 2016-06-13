/**
 * QueryTransfersService.java
 * 2016年5月19日
 * ©2015 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.service;

import com.tencent.common.Configure;
import com.tencent.protocol.mmpaymkttransfers.TransfersReqData;

/**
 * 查询企业转账服务类
 * 
 * @author wangda
 */
public class QueryTransfersService extends BaseService{

    public QueryTransfersService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        super(Configure.QUERY_TRANSFERS_API);
    }
    
    public String request(TransfersReqData req) throws Exception{
        return sendPost(req, true);
    }
}
