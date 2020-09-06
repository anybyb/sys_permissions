package com.managesys.permissions.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.page.interceptor.PagePo;
import com.managesys.permissions.annotation.PermissionsNotIntercept;
import com.managesys.permissions.entity.SysRoleEntity;
import com.managesys.permissions.service.IPermisManageServcie;

/**
 * 权限管理
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping("/permision/permisManage")
public class PermisManageController {
	 @Resource
	 private IPermisManageServcie permisServcie;
	 private static final Logger logger = Logger.getLogger(PermisManageController.class);
	 
	 @RequestMapping("/tojsp")
	 public ModelAndView tojsp(){
		ModelAndView modelAndView = new ModelAndView("/common/permisManage/index");
		return modelAndView;
	 }
	 
	 
	 @RequestMapping("/getRoleByPage.do")
	 public PagePo getRoleByPage(){
		List list =  permisServcie.getRoleByPage();
		return PagePo.getPagePo(list);
	 }
	 
	 /**
	  * 获取所有菜单(不拦截权限)
	  * @return
	  */
	 @RequestMapping("/noIntercept/getAllMenus.do")
	 @PermissionsNotIntercept
	 public List  getAllMenus(Integer roleId){
		 SysRoleEntity  entity = new SysRoleEntity();
		 if(roleId != null){
			 //编辑角色时，需要传入查询该角色已经拥有的权限集合，作为回显
			 entity.setId(roleId);
		 }
		List list =  permisServcie.getZtreeMenus(entity);
		return list;
	 }
	 
	 
	 
	 /**
	  * 查看角色名称是否被占用
	  * @return
	  */
	 @RequestMapping("/noIntercept/checkRoleName.do")
	 @PermissionsNotIntercept
	 public boolean  checkRoleName(String name,Integer id){
		 SysRoleEntity entity = new SysRoleEntity();
		 entity.setName(name);
		 SysRoleEntity  obj =  permisServcie.getSysRoleByFile(entity);
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
	  * 增加角色
	  */
	 @RequestMapping("/addRole.do")
	 public boolean addRole(String ids,String roleName){
		 try {
			String[] idss = ids.split(",");
			   permisServcie.addRole(idss,roleName);
		 } catch (Exception e) {
			 logger.error("添加角色的时候出现异常",e);
			 return false;
		} 
		   return true;
	 }
	 
	 /**
	  * 修改角色
	  */
	 @RequestMapping("/updateRole.do")
	 public boolean updateRole(String ids,String roleName,Integer roleId){
		try {
			String[] idss = ids.split(",");
			 if(null != roleId && roleId>0){
				 permisServcie.updateRole(idss,roleName,roleId); 
			 }else{
				 return false;
			 }
		} catch (Exception e) {
			logger.error("修改角色的时候出现异常",e);
			 return false;
		}
		 return true;
	 }
	 
	 
	 
	 /**
	  * 删除角色 直接物理删除
	  */
	 @RequestMapping("/deleteRole.do")
	 public boolean deleteRole(SysRoleEntity entity){
		 try {
			permisServcie.deleteRole(entity);  
		  } catch (Exception e) {
			logger.error("删除角色的时候出现异常",e);
			 return false;
		}
		 return true;
	 }
	
	
}
