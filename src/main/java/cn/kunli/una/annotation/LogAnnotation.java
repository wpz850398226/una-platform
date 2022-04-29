package cn.kunli.una.annotation;

import java.lang.annotation.*;

/**
 * 自定义日志审计注解
 * @author wangpz
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface LogAnnotation {
    String moduleName() default "";

    String methodType() default "";

    String idParamName() default "id";
}
