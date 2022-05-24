package cn.kunli.una.handler;

import cn.kunli.una.service.sys.SysExceptionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;

public class UnaResponseException extends JAXBException {

    @Autowired
    private SysExceptionService sysExceptionService;

    public UnaResponseException(String message) {
        super(message);
//        SysLoginAccountDetails loginAccount = UserUtil.getLoginAccount();
//        sysExceptionService.save((SysException) new SysException().setErrorInfo(message).setCreatorId(loginAccount.getId()));
    }

    public UnaResponseException(String message, String errorCode) {
        super(message, errorCode);
//        SysLoginAccountDetails loginAccount = UserUtil.getLoginAccount();
//        sysExceptionService.save((SysException) new SysException().setErrorInfo(message).setCode(errorCode).setCreatorId(loginAccount.getId()));
    }
}
