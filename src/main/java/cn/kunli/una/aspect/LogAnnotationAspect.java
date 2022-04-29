package cn.kunli.una.aspect;

import cn.kunli.una.annotation.LogAnnotation;
import cn.kunli.una.pojo.sys.SysEntity;
import cn.kunli.una.pojo.sys.SysLog;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.sys.SysLogService;
import cn.kunli.una.utils.common.UserUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义日志审计注解 切面类
 *
 * @author Ponzio
 */

@Aspect
@Component
@Slf4j
public class LogAnnotationAspect {

    @Resource
    private SysLogService sysLogService;

    @AfterReturning(value = "@annotation(cn.kunli.una.annotation.LogAnnotation)", returning = "result")
    public void saveLog(JoinPoint point, Object result) {
        SysEntity sysEntity = new SysEntity();
        //注解所在类 的实例对象
        Object classInstance = point.getThis();
        String classPath = classInstance.toString();

        try {
            sysEntity =(SysEntity) classInstance.getClass().getMethod("getEntity").invoke(classInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String packagePath = classPath.substring(0, classPath.lastIndexOf("."));
        String className = classPath.substring(classPath.lastIndexOf(".") + 1, classPath.lastIndexOf("@"));

        Signature signature = point.getSignature();
        String methodName = signature.getName();

        MethodSignature methodSignature = (MethodSignature) signature;

        Map<String, Object> params = new HashMap();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = point.getArgs();
        for (int i = 0; i < parameterNames.length; i++) {
            params.put(parameterNames[i], args[i]);
        }

        Method method = methodSignature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        String moduleName = logAnnotation.moduleName();
        String methodType = logAnnotation.methodType();
        //id入参名称
        String idParamName = logAnnotation.idParamName();

        String dataId = null;
        //获取指定入参名称的id
        Object idObj = params.get(idParamName);

        //如果指定的入参名称未获取到id且只有一个入参，则默认唯一入参即为id
        if (idObj == null && args.length == 1) {
            idObj = args[0];
        }

        if (idObj != null) {
            dataId = idObj.toString();
        }

        if (StringUtils.isBlank(moduleName)) {
            moduleName = "未知";
        }

        if (StringUtils.isBlank(methodType)) {
            if (methodName.contains("query") || methodName.contains("select")) {
                methodType = "查询";
            } else if (methodName.contains("save") || methodName.contains("insert")) {
                methodType = "新增";
            } else if (methodName.contains("delete") || methodName.contains("remove")) {
                methodType = "删除";
            } else if (methodName.contains("update") || methodName.contains("modify")) {
                methodType = "修改";
            } else {
                methodType = "未知";
            }
        }

        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();

        SysLog sysLog = new SysLog().setEntityId(sysEntity.getId()).setMethodType(methodType).setDataId(dataId)
                .setPackagePath(packagePath).setClassName(className).setMethodName(methodName)
                .setParam(JSON.toJSONString(params)).setResult(JSON.toJSONString(result));
        sysLog.setCreatorId(loginUser.getId()).setCreatorName(loginUser.getName()).setName(methodType+sysEntity.getName()+dataId);
        sysLogService.save(sysLog);

    }
}
