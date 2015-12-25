/**
 * QueryOpenIdBusiness.java
 * 2015年11月19日
 * ©2015 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.business;

import org.slf4j.LoggerFactory;

import com.tencent.common.Log;
import com.tencent.common.Util;
import com.tencent.protocol.tool_protocol.QueryOpenIdReqData;
import com.tencent.protocol.tool_protocol.QueryOpenIdResData;
import com.tencent.service.QueryOpenIdService;

/**
 * 根据授权码, 查询openId接口
 * @author wangda
 */
public class QueryOpenIdBusiness {
    private static Log log = new Log(LoggerFactory.getLogger(QueryOpenIdBusiness.class));
    
    private QueryOpenIdService queryOpenIdService;
    
    public QueryOpenIdBusiness() {
        try {
            queryOpenIdService = new QueryOpenIdService();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            log.e(e.toString());
        }
    }
    
    public QueryOpenIdResData request(QueryOpenIdReqData req) {
        String resp = "";
        try {
            resp = new QueryOpenIdService().request(req);
        } catch (Exception e) {
            log.e(e.toString());
            return null;
        }
        if (resp == null || resp.equals("")) {
            return null;
        }
        QueryOpenIdResData respData = (QueryOpenIdResData) Util.getObjectFromXML(resp, QueryOpenIdResData.class);
        return respData;
    }
}
