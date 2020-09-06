package com.managesys.permissions.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.managesys.permissions.annotation.PermissionsNotIntercept;
import com.managesys.permissions.entity.SysUserEntity;
import com.managesys.permissions.utils.SysConstant;

/**
 * 权限拦截器
 * @author wcc
 * @Date 2020年9月4日
 * @Description 
 *
 */
public class AuthPermisInterceptor  extends HandlerInterceptorAdapter{
	private static final Logger logger = Logger.getLogger(AuthPermisInterceptor.class);
	// 在调用方法之前执行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method  method  =  handlerMethod.getMethod();
			// 如果注解不为null, 说明不需要拦截,直接放过
			PermissionsNotIntercept permissions = method.getAnnotation(PermissionsNotIntercept.class); 
			if (permissions != null) {
			    return true; // 如果注解不为null, 说明不需要拦截,直接放过
			}else {
				//需要校验权限
				  /**
				   * 这种方式获取到的是类上+方法上的全相对路径，且resltful结构也支持只获取路径不获取参数
				   * String  oldUrl =(String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
				   */
				//获取类上的注解
				 RequestMapping  classMapping = handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
				 if(classMapping != null) {
					 String[]  calssUrls =  classMapping.value();
					 logger.debug("被请求的类上路径是："+Arrays.toString(calssUrls));
					 RequestMapping  requestMapping =	method.getAnnotation(RequestMapping.class);
					 if(requestMapping != null) {	 
						/** RequestMapping注解一个方法可以有多个请求地址 **/
				        String[]  urls = requestMapping.value();
				        logger.debug("被请求方法上路径是："+Arrays.toString(urls));
				        if(urls != null) {
				        		return  containsURL(calssUrls,urls, request, response);	
				        }else {
				        	//方法上未写地址
				        	if(calssUrls != null) {
								return  containsURL(calssUrls, null,request, response);
							}	
				        }	
					}else {
						//方法上未写注解
						if(calssUrls != null) {
							return  containsURL(calssUrls,null, request, response);
						}			
					} 
				 }
				 request.getRequestDispatcher("/message_prompt.html").forward(request, response);
			}
			
		} catch (Exception e) {
			 logger.error("权限拦截器异常",e);
		}
        return false;
    }
    
    
    /**
     * 
     * @Description: 对比session中权限和访问的路径
     * @param @param calssUrls  类上注解的地址数组 不能为空
     * @param @param urls 方法上注解的地址数组 可为空
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws ServletException
     * @param @throws IOException 参数
     * @return boolean 返回类型
     * @throws
     */
    private  boolean  containsURL(String[] calssUrls,String[] urls,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	   HttpSession session = request.getSession();
	   SysUserEntity  entity =   (SysUserEntity) session.getAttribute(SysConstant.SESSION_USER_NAME);
	   List accessList=(List) session.getAttribute(SysConstant.PERMISSIONS);
	   if(accessList != null ) {
		   for(String  calssUrl :calssUrls) {
			   if(urls != null) {
				   for(String url : urls) {
					   if(accessList.contains(calssUrl+url)) {
							return true;//权限验证通过
					  }
			        } 
			     }else {
				   if(accessList.contains(calssUrl)) {
						return true;//权限验证通过
				  }
			   }
			   
		   }
	   }  
	   logger.warn("用户【"+entity.getName()+"】发送了一个无权限请求");
	   request.getRequestDispatcher("/message_prompt.html").forward(request, response);
	   return false;
    }

}
