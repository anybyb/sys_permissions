package com.managesys.permissions.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法此注解,不拦截系统权限
 * @author wcc
 * @Date 2020年9月4日
 * @Description 
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionsNotIntercept {

}
