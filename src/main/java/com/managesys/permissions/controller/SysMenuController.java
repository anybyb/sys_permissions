package com.managesys.permissions.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.managesys.permissions.annotation.PermissionsNotIntercept;
import com.managesys.permissions.entity.SysMenuEntity;
import com.managesys.permissions.service.ISysMenuService;

/**
 * 系统菜单管理
 * 一般提供给开发人员使用，在项目完成后这个可能用不上
 * @author wcc
 *
 */
@Controller
@RequestMapping("/permision/sysMenu")
@ResponseBody
public class SysMenuController {
	 private static final Logger logger = Logger.getLogger(SysMenuController.class);
	@Resource
    private ISysMenuService  sysMenuService;
	@RequestMapping("/tojsp.do")
	public ModelAndView tojsp(){
		ModelAndView modelAndView = new ModelAndView("/common/sysMenu/index");
		return modelAndView;	
	}
	
	@RequestMapping("/getTreeMenus.do")
	public List getTreeMenus(){
	    List list =	sysMenuService.getTreeMenus();
	    return list;
	}
	
	/**
	 * 校验名称是否被占用
	 * 该方法不被拦截
	 * @return
	 */
	@RequestMapping("/noIntercept/checkMenuName.do")
	@PermissionsNotIntercept
	public boolean checkMenuName(Integer menuId, String  menuName ){
		 SysMenuEntity entity = new SysMenuEntity();
		 entity.setName(menuName);
		 SysMenuEntity  obj =  sysMenuService.getSysMenuByFile(entity);
		 if(obj != null){
			 //判断是增加还是修改
			 if(obj.getId() == menuId){
				 //修改
				 return true;
			 }else{
				 return false;
			 }
			 
		 }
		return true;
	}
	
	/**
	 * 添加菜单或者动作 (仅限于系统开发人员使用)
	 * @param  当前选择的menu的id就是添加的父菜单，如果是空则为一级菜单
	 * @param 
	 * @return 
	 */
	@RequestMapping("/addMenu.do")
	public boolean addMenu(SysMenuEntity entity){
		  try {
			SysMenuEntity menuEntity = new SysMenuEntity();
			  menuEntity.setPid(entity.getId() == null  ?  0 : entity.getId());
			  menuEntity.setCreateTime(new Date());
			  menuEntity.setPath(entity.getPath());
			  menuEntity.setName(entity.getName());
			  sysMenuService.addMenu(menuEntity);
		} catch (Exception e) {
			 logger.error("添加菜单的时候出现异常",e);
			 return false;
		}
		  return true;
	}
	
	/**
	 * 修改菜单或者动作 根据当前的菜单的id(仅限于系统开发人员使用)
	 * @param  
	 * @param 
	 * @return 
	 */
	@RequestMapping("/updateMenu.do")
	public boolean updateMenu(SysMenuEntity entity){
		  try {
			  sysMenuService.updateMenu(entity);
		} catch (Exception e) {
			 logger.error("修改菜单的时候出现异常",e);
			 return false;
		}
		  return true;
	}
	
	
	
	/**
	 * 修改菜单名称 系统用户可使用
	 * @param  
	 * @param 
	 * @return 
	 */
	@RequestMapping("/updateMenuName.do")
	public boolean updateMenuName(SysMenuEntity entity){
		  try {
			  sysMenuService.updateMenuName(entity);
		 } catch (Exception e) {
			 logger.error("修改菜单的时候出现异常",e);
			 return false;
		 }
		  return true;
	}
	
	
	
	
	
	/**
	 * 删除菜单或者动作 根据当前的菜单的id(仅限于系统开发人员使用)
	 * @param  
	 * @param 
	 * @return 
	 */
	@RequestMapping("/deleteMenu.do")
	public boolean deleteMenu(Integer id){
		
	 try {
		 if(id != null){
			 sysMenuService.deleteMenu(id);
		 }	  
		} catch (Exception e) {
			 logger.error("删除菜单的时候出现异常",e);
			 return false;
		}
		  return true;
	}
	
	
	
	
	
	
	
	
	
}
