package com.managesys.permissions.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解：用法和shiro的权限注解一样</br>
 * 使用方法在controller的方法上写注解.例：@Permissions（"/lanjie/lujin"）</br>
 * [暂未使用]
 * @describe 
 * @author wcc
 * @data 2018年1月8日
 * @version v1.0
 * @copyright LH.GROUP
 */
@Deprecated
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permissions {
    
    public  String  value() default "";//权限的路径值
    

}
