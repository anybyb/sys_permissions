package com.managesys.permissions.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.managesys.permissions.dao.IPermisManageDao;
import com.managesys.permissions.entity.SysMenuEntity;
import com.managesys.permissions.entity.SysMenuVo;
import com.managesys.permissions.entity.SysRoleEntity;
import com.managesys.permissions.entity.SysRoleMenuEntity;
import com.managesys.permissions.entity.SysUserEntity;
import com.managesys.permissions.entity.SysZTreeMenu;
import com.managesys.permissions.service.IPermisManageServcie;
/**
 * 
 * @author wcc
 * 服务层
 *
 */
@Service("permisServcie")
public class PermisManageServcieImpl implements IPermisManageServcie {
     @Resource
    private IPermisManageDao  permisManageDao;
     
	public Map<String, Object> getUSerMenus(int userId) {
		   SysUserEntity entity = new SysUserEntity();
		   entity.setId(userId);
		   List<SysMenuEntity> list = 	permisManageDao.findUserPermissions(entity);
		   return parsingMenus(list);
	}

	public List getRoleByPage() {
		return permisManageDao.getRoleByPage();
	}
	
	 /**
	   * 		{ id:1, pId:0, name:"权限管理", open:true},
	     		{ id:11, pId:1, name:"系统用户管理", open:true},
	     		{ id:111, pId:11, name:"添加用户"},
	     		{ id:112, pId:11, name:"删除用户"},
	     		{ id:12, pId:1, name:"角色管理", open:true},
	     		{ id:121, pId:12, name:"添加角色"},
	     		{ id:122, pId:12, name:"删除角色"},
	   */
	
	public List getZtreeMenus(SysRoleEntity entity) {
	       List<SysMenuEntity> list  = 	permisManageDao.findAllMenus();
		   List<SysZTreeMenu> zTreeList = new ArrayList<SysZTreeMenu>();
		   Iterator<SysMenuEntity> iterator = list.iterator();
		    List<SysMenuEntity> userPermissionsList = new ArrayList<SysMenuEntity>();
		   if(entity.getId() != null){
			 //获取用户拥有的权限集合
		     userPermissionsList  = permisManageDao.findRolePermissions(entity); 
		  }
		  if(userPermissionsList.size() != 0){
			  //用户有权限，进去设置其为默认选中
			  while (iterator.hasNext()) {
				   SysMenuEntity sysMenuEntity =  iterator.next(); 
				   SysZTreeMenu  sysZTreeMenu = new  SysZTreeMenu();
				   for(int i=0;i<userPermissionsList.size();i++){
					    if(userPermissionsList.get(i).getId() == sysMenuEntity.getId().intValue()){
						  sysZTreeMenu.setChecked(true);//选中已有的节点
						  sysZTreeMenu.setOpen(true);//选中的默认展开
						  //entity.getId().intValue()==1 是我们初始的admin
						  if(entity.getId().intValue() == 1  && ( userPermissionsList.get(i).getId().intValue() == 1 ||  userPermissionsList.get(i).getId().intValue() == 2 || userPermissionsList.get(i).getPid().intValue() == 2)){
							  //admin 的 角色管理不能进行修改
							  sysZTreeMenu.setChkDisabled(true); 
						  }
						
						  break;
					    } 
				   }
				   sysZTreeMenu.setId(sysMenuEntity.getId());
				   sysZTreeMenu.setpId(sysMenuEntity.getPid());
				   sysZTreeMenu.setName(sysMenuEntity.getName());
				   zTreeList.add(sysZTreeMenu);
				 }
		  }else{
			   while (iterator.hasNext()) {
				   SysZTreeMenu  sysZTreeMenu = new  SysZTreeMenu();
				   SysMenuEntity sysMenuEntity =  iterator.next(); 
				   sysZTreeMenu.setId(sysMenuEntity.getId());
				   sysZTreeMenu.setpId(sysMenuEntity.getPid());
				   sysZTreeMenu.setName(sysMenuEntity.getName());
				   zTreeList.add(sysZTreeMenu);
				 }
		  }
		
		  return zTreeList;
	}
	
	
	/***
	 * 组织菜单树Meuns
	 * @param list
	 * @return
	 */
	private  Map<String, Object> parsingMenus(List list){
		 Map<String, Object>   map =  new HashMap<String, Object>();
		   //组织菜单树
		   Iterator<SysMenuEntity> iterator = list.iterator();
		   List<SysMenuEntity> parentMenuList = new ArrayList<SysMenuEntity>();
		   List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();
		   List<String> paths = new ArrayList<String>();
		   while (iterator.hasNext()) {
			   SysMenuEntity sysMenuEntity =  iterator.next(); 
			   paths.add(sysMenuEntity.getPath());
			   if(sysMenuEntity.getPid() == 0){
				   //父菜单
				   parentMenuList.add(sysMenuEntity);
			   }else{
				   //子菜单
				   subMenuList.add(sysMenuEntity);
				}
			 }
		   
		   List<SysMenuVo> sysMenuVoList = new ArrayList<SysMenuVo>();
		   //循环两个list
		   for(int i =0;i<parentMenuList.size();i++){
			   SysMenuVo sysMenuVo  = new SysMenuVo();
			   SysMenuEntity  sysMenuEntity = parentMenuList.get(i);
			   BeanUtils.copyProperties(sysMenuEntity, sysMenuVo);
			   List<SysMenuVo> subMenuVoList = new ArrayList<SysMenuVo>();
			   for(int x =0;x<subMenuList.size();x++){
				   SysMenuEntity  subMenuEntity = subMenuList.get(x);
				   if(subMenuEntity.getPid().intValue() == sysMenuVo.getId().intValue()){
					   //是其子菜单
					   SysMenuVo subMenuVo  = new SysMenuVo();
					   BeanUtils.copyProperties(subMenuEntity, subMenuVo);   
					   subMenuVoList.add(subMenuVo);
				   }
			   }
			   sysMenuVo.setSubMenu(subMenuVoList);
			   sysMenuVoList.add(sysMenuVo);
		   }
           map.put("paths", paths); 
		   map.put("menuList", sysMenuVoList);
		   return map;
	}
	
	public SysRoleEntity getSysRoleByFile(SysRoleEntity entity) {
		
		return permisManageDao.getSysRoleByFile(entity);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void addRole(String[] ss, String roleName) {
		SysRoleEntity roleEntity = new SysRoleEntity();
		roleEntity.setName(roleName);
		roleEntity.setCreateTime(new Date());
		permisManageDao.insertRole(roleEntity);
		//插入该角色对应的菜单权限
		List<SysRoleMenuEntity> list = new ArrayList<SysRoleMenuEntity>();
		for(int i=0;i<ss.length;i++){
			SysRoleMenuEntity sysRoleMenu = new SysRoleMenuEntity();
			sysRoleMenu.setRid(roleEntity.getId());
			sysRoleMenu.setMid(Integer.parseInt(ss[i]));
			list.add(sysRoleMenu);
		}
		permisManageDao.insertsRoleMenu(list);
	
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void updateRole(String[] ss, String roleName, Integer roleId) {
		SysRoleEntity roleEntity = new SysRoleEntity();
		roleEntity.setId(roleId);
		roleEntity.setName(roleName);
		permisManageDao.updateRoleById(roleEntity);
		//先删除 ---再插入 角色对应的菜单权限
		
		SysRoleMenuEntity  roleMenuEntity = new SysRoleMenuEntity();
		roleMenuEntity.setRid(roleId);
		permisManageDao.delRoleMenuByFile(roleMenuEntity);
		
		List<SysRoleMenuEntity> list = new ArrayList<SysRoleMenuEntity>();
		for(int i=0;i<ss.length;i++){
			SysRoleMenuEntity sysRoleMenu = new SysRoleMenuEntity();
			sysRoleMenu.setRid(roleEntity.getId());
			sysRoleMenu.setMid(Integer.parseInt(ss[i]));
			list.add(sysRoleMenu);
		}
		
		//如果是admin 角色管理是必须有的  admin的id为1
		if(roleEntity.getId().intValue() == 1){
			String[] admins = {"1","2","3","4","5","6"};//对应角色管理的各个菜单的id
			for(int i=0;i<admins.length;i++){
				SysRoleMenuEntity sysRoleMenu = new SysRoleMenuEntity();
				sysRoleMenu.setRid(roleEntity.getId());
				sysRoleMenu.setMid(Integer.parseInt(admins[i]));
				list.add(sysRoleMenu);
			}
		}
		//admin的操作 end 
		
		permisManageDao.insertsRoleMenu(list);
		
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void deleteRole(SysRoleEntity entity) {
		 permisManageDao.delRoleById(entity);
	     SysRoleMenuEntity 	roleMenuEntity = new SysRoleMenuEntity();
	     roleMenuEntity.setRid(entity.getId());
		 permisManageDao.delRoleMenuByFile(roleMenuEntity);
		
	}
	
	
	public List getAllSysRole() {
		return 	permisManageDao.getAllSysRole();
	}


    
}
