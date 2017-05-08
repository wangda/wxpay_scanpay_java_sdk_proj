package com.tencent.common.report;

import com.tencent.common.report.service.ReportService;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

/**
 * User: rizenguo
 * Date: 2014/12/3
 * Time: 16:34
 */
public class ReportRunable implements Runnable {

    private ReportService reportService ;

    ReportRunable(ReportService rs){
        reportService = rs;
    }

    @Override
    public void run() {
        try {
            reportService.request();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
