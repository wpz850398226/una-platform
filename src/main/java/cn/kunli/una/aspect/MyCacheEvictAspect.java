package cn.kunli.una.aspect;

import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * 自定义清除缓存注解实现类
 *
 * @author Ponzio
 */

@Aspect
@Component
@Slf4j
public class MyCacheEvictAspect {

    @Autowired
    private RedisUtil redisUtil;


    @AfterReturning("@annotation(cn.kunli.una.annotation.MyCacheEvict)")
    public void remove(JoinPoint point) {
        String className = point.getThis().toString();
        String serviceName = className.substring(className.lastIndexOf(".")+1,className.lastIndexOf("@"));
        Signature signature = point.getSignature();
        Method method = ((MethodSignature) signature).getMethod();
        MyCacheEvict myCacheEvict = method.getAnnotation(MyCacheEvict.class);
        String[] keys = myCacheEvict.value();
        for (String key : keys) {
            key = key+"::"+serviceName+":*";
            Set set = redisUtil.hasKeys(key);
            redisUtil.delKeys(set);
            log.info("==========> cache key {} deleted" ,key);
        }
    }
}
