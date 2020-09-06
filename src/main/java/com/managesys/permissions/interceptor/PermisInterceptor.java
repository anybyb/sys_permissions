package com.managesys.permissions.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.managesys.permissions.entity.SysUserEntity;

/**
 * 拦截路径的方式来拦截【暂时未使用到，作为案例备份】
 * 权限拦截器
 * 拦截以.do结尾的请求中不包含"/noIntercept/"的请求
 * 如果页面是ajax请求，请自行处理，拦截器将返回501表示没有权限
 * 调用ajax的     complete :function (XMLHttpRequest,textStatus){
		    	  if(textStatus=="error"){
		    		 //TODO
		    		}
		      }
 * @author wcc
 *
 */
@Deprecated
public class PermisInterceptor  implements HandlerInterceptor{
	private static final Logger logger = Logger.getLogger(PermisInterceptor.class);

	public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse response, Object handler){
		          HttpSession session = httpRequest.getSession();
				 //用户登录后做细粒度判断 是否拥有此权限
				 String url= httpRequest.getServletPath(); 
				 logger.info("权限拦截器执行拦截-->拦截到路径是："+url);
				 SysUserEntity  entity =   (SysUserEntity) session.getAttribute("user");
				 List accessList=(List) session.getAttribute("permissions");
				try {
				  if(url.contains("/noIntercept/") ||  accessList.contains(url)){
					 return true;
				   }else {
					   //判断是否是ajax请求：
						  String isAjax =   httpRequest.getHeader("x-requested-with");
						  if("XMLHttpRequest".equals(isAjax)){
							  //用户没有权限 ajax返回501，页面自行处理
							  logger.warn("用户【"+entity.getName()+"】发送了一个无权限ajax请求");
							  response.setStatus(501);  
						  }else{
							    logger.warn("用户【"+entity.getName()+"】发送了一个无权限请求");
							    httpRequest.getRequestDispatcher("/message_prompt.jsp").forward(httpRequest, response);
						  }
				 }
			} catch (Exception e) {
				  logger.error("权限拦截器异常",e);
			}finally {
				//记录日志
				
				
			}
			return false;
				
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
	}

}
