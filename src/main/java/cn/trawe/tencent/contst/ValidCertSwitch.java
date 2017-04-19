package cn.trawe.tencent.contst;

/**
* @author 赵友强 
* @version 创建时间：2017年4月18日 下午1:46:18
* 类说明
* 控制微信sdk是否检查证书
*/

public class ValidCertSwitch {

    public static boolean isValidCert = true;

    public boolean getIsValidCert() {
        return isValidCert;
    }

    public void setIsValidCert(boolean isValidCert) {
        ValidCertSwitch.isValidCert = isValidCert;
    }

    
    
}
