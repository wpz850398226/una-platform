package cn.kunli.una.annotation;

import java.lang.annotation.*;

/**
 * 日志注解，Controller使用此注解，操作生成日志
 * @author hudahua
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface LogAnnotation {

}
