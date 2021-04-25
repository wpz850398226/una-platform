package cn.kunli.una.aspect;

import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
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
        Class<? extends Signature> aClass = point.getSignature().getClass();
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        MyCacheEvict myCacheEvict = method.getAnnotation(MyCacheEvict.class);
        String[] keys = myCacheEvict.value();
        for (String key : keys) {
            key = key+"::"+aClass.getName()+":"+method.getName()+":*";
            Set set = redisUtil.hasKeys(key);
            redisUtil.delKeys(set);
            log.info("cache key: " + key + " deleted");
        }
    }
}
