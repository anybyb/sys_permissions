package com.managesys.permissions.service;

import java.util.List;
import java.util.Map;

import com.managesys.permissions.entity.SysRoleEntity;

/**
 * 权限管理服务层
 * @author wcc
 *
 */
public interface IPermisManageServcie {
    /**
     * 返回一个map</br>
     * 其中两个key  如下：</br>
     * key:paths  表示菜单操作路径集合  </br>
     * key:menuList 表示所有菜单对象集合
     * @param 用户id
     * @return
     */
	Map<String, Object> getUSerMenus(int  userId);
   /**
    * 获得角色列表
    * @return
    */
	List getRoleByPage();
	/**
	 * 获取系统所有的菜单</br>
	 * 在角色授权时，利用ztree来展现</br>
	 * 数据库查询出来的刚好就是其要的结构.但是要稍微做修改</br>
	 *  @param entity 角色对象，传入角色id即可
	 * @return
	 */
	List getZtreeMenus(SysRoleEntity entity);
	/**
	 * 查询单个角色对象
	 * @param entity
	 * @return
	 */
	SysRoleEntity getSysRoleByFile(SysRoleEntity entity);
	/**
	 * 添加角色
	 * @param roleId 
	 * @param ss 角色对应的权限集合
	 * @param name 角色名称
	 */
	void addRole(String[] idss,  String roleName);
	/**
	 * 修改角色
	 * @param idss  角色拥有的菜单id集合
	 * @param roleName  角色名称
	 * @param roleId  角色id
	 */
	void updateRole(String[] idss, String roleName, Integer roleId);
	/**
	 * 删除角色，删除其对应的菜单关联
	 * @param entity
	 */
	void deleteRole(SysRoleEntity entity);
	/**
	 * 获取系统所有的角色
	 * @return
	 */
	List getAllSysRole();

}
