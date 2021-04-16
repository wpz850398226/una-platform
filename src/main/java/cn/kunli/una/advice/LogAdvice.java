package cn.kunli.una.advice;

import cn.kunli.una.pojo.system.SysLog;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.system.SysLogService;
import cn.kunli.una.utils.common.UserUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * @program: 全局异常处理
 * @author: Ponzio
 * @create: 2020-07-20 10:13
 */
@RestControllerAdvice(basePackages = {"cn.kunli.una.controller"})
public class LogAdvice {

    @Autowired
    private SysLogService sysLogService;


    /**
     * 空指针异常处理
     *
     * @param point
     * @return
     */
    @Around(value = "@annotation(cn.kunli.una.annotation)")
    public Object logSave(ProceedingJoinPoint point) {
        MethodSignature methodSignature = (MethodSignature)point.getSignature();
        String name = methodSignature.getMethod().getName();
        /*Class<?>[] parameterTypes = methodSignature.getMethod().getParameterTypes();
        Parameter[] parameters = methodSignature.getMethod().getParameters();*/

        SysLog sysLog = (SysLog) new SysLog().setType(name).setCreateTime(new Date());
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        if(loginUser!=null)sysLog.setCreatorId(loginUser.getId());


        try {
            Object proceed = point.proceed();
            sysLog.setIsSuccess(1);
            sysLogService.save(sysLog);
            return proceed;

        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

}
