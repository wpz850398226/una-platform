package cn.kunli.una.annotation;

import java.lang.annotation.*;

/**
 * 自定义清除缓存注解，可根据自定义缓存驱逐器规则，批量清除缓存
 * @author Ponzio
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface MyCacheEvict {
    String[] value() default {};

    String keyGenerator() default "";
}
