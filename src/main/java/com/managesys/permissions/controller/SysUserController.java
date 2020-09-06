package com.managesys.permissions.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.common.utils.MD5Util;
import com.basic.page.interceptor.PagePo;
import com.managesys.permissions.entity.SysRoleEntity;
import com.managesys.permissions.entity.SysUserDto;
import com.managesys.permissions.entity.SysUserEntity;
import com.managesys.permissions.entity.SysUserSearch;
import com.managesys.permissions.service.IPermisManageServcie;
import com.managesys.permissions.service.ISysUserService;
import com.managesys.permissions.utils.SysConstant;

/**
 * 后台用户
 * @author wcc
 */
@Controller
@RequestMapping("/permision/sysUser")
@ResponseBody
public class SysUserController {
	 @Resource
	 private ISysUserService sysUserService;
	 @Resource
	 private IPermisManageServcie permisServcie; 
	 private static final Logger logger = Logger.getLogger(SysUserController.class);
	
	@RequestMapping("/tojsp.do")
	public ModelAndView tojsp(){
		ModelAndView modelAndView = new ModelAndView("/common/sysUser/index");
		return modelAndView;	
	}
	

	@RequestMapping("/noIntercept/getSysUserByPage.do")
	public PagePo getSysUserByPage(SysUserSearch sysUserSearch){
		List list = sysUserService.getSysUserByPage(sysUserSearch);
		return PagePo.getPagePo(list);
	}
	
    /**
     * 获取系统所有的角色
     * @param sysUserSearch
     * @return
     */
	@RequestMapping("/noIntercept/getSysRole.do")
	public List getSysRole(){
		SysRoleEntity roleEntity = new SysRoleEntity();
		roleEntity.setId(-1);
		roleEntity.setName("--请选择--");
		List list = permisServcie.getAllSysRole();
		list.add(0, roleEntity);
		return list;
	}
	
    
	
	 /**
     * 检查用户名是否被占用
     * @param sysUserSearch
     * @return
     */
	@RequestMapping("/noIntercept/checkSysUserName.do")
	public boolean checkSysUserName(String name,Integer id){
		 SysUserEntity entity = new SysUserEntity();
		 entity.setName(name);
		 SysUserEntity  obj =  sysUserService.getSysUserByFile(entity);
		 if(obj != null){
			 //判断是增加还是修改
			 if(obj.getId() == id){
				 //修改
				 return true;
			 }else{
				 return false;
			 } 
		 }
		return true;
	 }
	
	
	/**
     * 添加操作
     * @param sysUserEntity
     * @return
     */
	@RequestMapping("/addSysUser.do")
	public boolean addSysUser(SysUserDto  sysUserDto){
		          if("--请选择--".equals(sysUserDto.getCity()) || StringUtils.isBlank(sysUserDto.getCity())){
		    	   sysUserDto.setCity(null);
		          }
				 sysUserService.addSysUser(sysUserDto);
				 return  true;
		} 
	
	/**
     * 修改操作
     * @param sysUserEntity
     * @return
     */
	@RequestMapping("/updateSysUser.do")
	public boolean updateSysUser(SysUserDto  sysUserDto){
		          if("--请选择--".equals(sysUserDto.getCity()) || StringUtils.isBlank(sysUserDto.getCity())){
		    	   sysUserDto.setCity("");
		          }
				 sysUserService.updateSysUser(sysUserDto);
				 return  true;
		} 
	
	/**
     * 删除操作
     * @param sysUserEntity
     * @return
     */
	@RequestMapping("/deleteSysUser.do")
	public boolean deleteSysUser(SysUserDto  sysUserDto){
				 sysUserService.deleteSysUser(sysUserDto);
				 return  true;
		} 
	
	
	
    /**
     * 个人中心---修改密码--校验旧密码是否正确
     * @param 
     * @return
    
	@RequestMapping("/noIntercept/checkOldPwd.do")
	public boolean checkOldPwd(SysUserDto  sysUserDto,HttpSession session){ 
		SysUserEntity entity = (SysUserEntity) session.getAttribute(SysConstant.SESSION_USER_NAME);	
		logger.info("系统用户修改密码：传入的旧密码"+sysUserDto.getPassword());
	    if(entity.getPassword().equals(MD5Util.encode(sysUserDto.getPassword()))){
	    	//验证通过
	    	logger.info("修改密码校验旧密码通过");
	    	return true;
	    }else{
	     	logger.info("修改密码校验旧密码未通过");
	    	return false;
	    }		
	 }
	 */
	
    /**
     * 个人中心---提交修改密码
     * @param 
     * @return
     */
	@RequestMapping("/noIntercept/updateUserPwd.do")
	public Map<String,Object> checkOldPwd(Integer id,String oldPwd,String newPwd,HttpSession session){ 
		SysUserEntity entity = (SysUserEntity) session.getAttribute(SysConstant.SESSION_USER_NAME);	
		logger.info("系统用户修改密码：newPwd"+newPwd+"oldPwd"+oldPwd+"id"+id);
		Map<String,Object>  map = new HashMap<String,Object> ();
		if(entity.getId() !=  id){
			//判断是不是修改自己的东西
			logger.info("无权修改");
			map.put("result", false);
			map.put("msg", "你无权修改该用户的密码");
			return map;
		}else if(!entity.getPassword().equals(MD5Util.encode(oldPwd))){
	    	//验证通过
	    	logger.info("修改密码校验旧密码未通过");
	    	map.put("result", false);
			map.put("msg", "输入的旧密码不正确");
			return map;
	    }else{
	    	//可进行修改
	    	SysUserDto sysUser = new  SysUserDto();
	    	sysUser.setId(entity.getId());
	    	sysUser.setPassword(MD5Util.encode(newPwd));
	    	sysUserService.updateSysUserPwd(sysUser);
	    	map.put("result", true);
	    	return map;
	    }		
	 }
	
	
	/**
     * 退出系统
     * @param 
     * @return
     */
	@RequestMapping("/noIntercept/logOut.do")
	 public Map<String,Object> logOut(SysUserDto  sysUserDto,HttpSession session){ 
		SysUserEntity entity = (SysUserEntity) session.getAttribute(SysConstant.SESSION_USER_NAME);	
		Map<String,Object>  map = new HashMap<String,Object> ();
		if(entity.getId() !=  sysUserDto.getId()){
			//判断是不是修改自己的东西
			logger.info("操作的不是当前登录者的id");
			map.put("result", false);
			map.put("msg", "你无权修改进行此操作");
			return map;	
	   }else{
		    session.invalidate();
			map.put("result", true);
			map.put("msg", "退出成功");
			return map;	
	   }
	 }
	
	}
	