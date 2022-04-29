package cn.kunli.una.handler;

import cn.kunli.una.service.sys.SysExceptionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;

public class UnaException extends JAXBException {

    @Autowired
    private SysExceptionService sysExceptionService;

    public UnaException(String message) {
        super(message);
//        SysLoginAccountDetails loginAccount = UserUtil.getLoginAccount();
//        sysExceptionService.save((SysException) new SysException().setErrorInfo(message).setCreatorId(loginAccount.getId()));
    }

    public UnaException(String message, String errorCode) {
        super(message, errorCode);
//        SysLoginAccountDetails loginAccount = UserUtil.getLoginAccount();
//        sysExceptionService.save((SysException) new SysException().setErrorInfo(message).setCode(errorCode).setCreatorId(loginAccount.getId()));
    }
}
