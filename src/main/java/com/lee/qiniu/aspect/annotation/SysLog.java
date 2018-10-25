package com.lee.qiniu.aspect.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @Description: 作用于方法上的注解
 * @author Jussi Lee
 * @date 2018年10月25日
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
	/***菜单目录****/
	String module() default "";
	EMethodType type();
}
