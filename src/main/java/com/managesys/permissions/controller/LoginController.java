package com.managesys.permissions.controller;

import java.util.Map;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.managesys.permissions.annotation.PermissionsNotIntercept;
import com.managesys.permissions.entity.SysUserEntity;
import com.managesys.permissions.service.ILoginService;


/**
 * 后台管理用户登录</br>
 * 该类中的方法都不被拦截
 * 
 * @author wcc
 *
 */
@Controller
@RequestMapping("/permision/manageLogin")
@ResponseBody
public class LoginController {
	@Resource
	private ILoginService loginService;

	@RequestMapping("/login")
	@PermissionsNotIntercept
	public Map<String, Object> login(SysUserEntity sysUserEntity, HttpSession session) {

		// 用户登录
		Map<String, Object> map = loginService.login(sysUserEntity);
		String status = (String) map.get("status");
		if ("0".equals(status)) {
			// 登录失败
		} else {
			// 登录成功
			session.setAttribute("user", map.get("user"));
			session.setAttribute("permissions", map.get("paths"));
		}
		return map;
	}

	@RequestMapping("/home")
	@PermissionsNotIntercept
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/common/home/home");
		String param = request.getParameter("menu");
		JSONArray data = JSONArray.parseArray(param);
		System.out.println("------------->" + data);
		modelAndView.addObject("menuList", data);
		return modelAndView;
	}
}
