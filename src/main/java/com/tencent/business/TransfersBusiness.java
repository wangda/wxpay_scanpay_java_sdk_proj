/**
 * QueryOpenIdBusiness.java
 * 2015年11月19日
 * ©2015 北京特微智能科技有限公司. All rights reserved.
 */
package com.tencent.business;

import org.slf4j.LoggerFactory;

import com.tencent.common.Log;
import com.tencent.common.Util;
import com.tencent.protocol.mmpaymkttransfers.TransfersReqData;
import com.tencent.protocol.mmpaymkttransfers.TransfersRespData;
import com.tencent.service.TransfersService;

/**
 * 企业转账
 * @author wangda
 */
public class TransfersBusiness {
    private static Log log = new Log(LoggerFactory.getLogger(TransfersBusiness.class));
    
    private TransfersService transfersService;
    
    public TransfersBusiness() {
        try {
            transfersService = new TransfersService();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            log.e(e.toString());
        }
    }
    
    public TransfersRespData run(TransfersReqData req) {
        String resp = "";
        try {
            resp = transfersService.request(req);
        } catch (Exception e) {
            log.e(e.toString());
            return null;
        }
        if (resp == null || resp.equals("")) {
            return null;
        }
        TransfersRespData respData = (TransfersRespData) Util.getObjectFromXML(resp, TransfersRespData.class, true);
        return respData;
    }
}
