package cn.kunli.una.aspect;

import cn.hutool.core.util.StrUtil;
import cn.kunli.una.annotation.LogAnnotation;
import cn.kunli.una.pojo.sys.SysEntity;
import cn.kunli.una.pojo.sys.SysLog;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.sys.SysLogService;
import cn.kunli.una.utils.common.UserUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
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
//    @Resource
//    private Map<String,String> globalDictionaryMap;

    @Async
    @AfterReturning(value = "@annotation(cn.kunli.una.annotation.LogAnnotation)", returning = "result")
    public void saveLog(JoinPoint point, Object result) throws Throwable {
        SysEntity sysEntity = new SysEntity();
        //注解所在类 的实例对象
        Object classInstance = point.getThis();
        //获取类的全路径
        String classPath = classInstance.toString();
        //获取包路径
        String packagePath = classPath.substring(0, classPath.lastIndexOf("."));
        //获取类名
        String className = classPath.substring(classPath.lastIndexOf(".") + 1, classPath.lastIndexOf("@"));
        Signature signature = point.getSignature();
        //获取方法名
        String methodName = signature.getName();
        MethodSignature methodSignature = (MethodSignature) signature;

        //获取所有入参，存入map中
        Map<String, Object> params = new HashMap();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = point.getArgs();
        for (int i = 0; i < parameterNames.length; i++) {
            params.put(parameterNames[i], args[i]);
        }

        Method method = methodSignature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);

        //获取指定的模块名称
        try {
            sysEntity =(SysEntity) classInstance.getClass().getMethod("getEntity").invoke(classInstance);
        } catch (NoSuchMethodException e) {
            log.warn("日志记录无法获取实体类");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String methodType = logAnnotation.methodType();
        //id入参名称
        String idParamName = logAnnotation.idParamName();

        String dataId = null;
        String typeDcode = null;
        //获取指定入参名称的id
        Object idObj = params.get(idParamName);

        //如果指定的入参名称未获取到id且只有一个入参，则默认唯一入参即为id
        if (idObj == null && args.length == 1) {
            idObj = args[0];
        }

        if (idObj != null && args[0] instanceof Integer) {
            dataId = idObj.toString();
        }

        //如果没有指定操作类型，则通过方法名称判断操作类型
        if (StrUtil.isBlank(methodType)) {
            if (methodName.contains("login")) {
                methodType = "登录";
            } else if (methodName.contains("logout")) {
                methodType = "登出";
            } else if (methodName.contains("save") || methodName.contains("insert")) {
                methodType = "新增";
            } else if (methodName.contains("delete") || methodName.contains("remove")) {
                methodType = "删除";
            } else if (methodName.contains("update") || methodName.contains("modify")) {
                methodType = "修改";
//                methodType = globalDictionaryMap.get("log_operate:修改");
            } else if (methodName.contains("query") || methodName.contains("select") || methodName.contains("get")) {
                methodType = "查询";
            } else {
                methodType = "未知";
            }
        }

        //获取当前登录用户
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();

        //通过操作类型，区分并设置日志类型
        switch (methodType) {
            case "登录":
            case "登出":
                typeDcode = "authentication";
//                body.put("result", JSONUtil.toJsonStr(result));
                break;
            case "新增":
            case "导入":
            case "删除":
            case "修改":
                //日志保存执行结果
//                body.put("result", JSONUtil.toJsonStr(result));
            case "查询":
            case "导出":
                typeDcode = "business";
                break;
            default:
                typeDcode = "unknown";
                break;
        }

        SysLog sysLog = new SysLog().setEntityId(sysEntity.getId())
                .setTypeDcode(typeDcode)
                .setMethodTypeDcode(methodType)
                .setDataId(dataId)
                .setPackagePath(packagePath)
                .setClassName(className)
                .setMethodName(methodName)
                .setParam(JSON.toJSONString(params))
                .setResult(JSON.toJSONString(result));
        if(loginUser!=null){
            sysLog.setIpAddress(loginUser.getLoginIp())
                    .setCreatorId(loginUser.getId())
                    .setCreatorName(loginUser.getName())
                    .setName(methodType+sysEntity.getName()+System.currentTimeMillis());
        }

        sysLogService.save(sysLog);
    }
}
