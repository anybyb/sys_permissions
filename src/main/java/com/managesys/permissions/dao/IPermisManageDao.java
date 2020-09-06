package com.managesys.permissions.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.managesys.permissions.entity.SysMenuEntity;
import com.managesys.permissions.entity.SysRoleEntity;
import com.managesys.permissions.entity.SysRoleMenuEntity;
import com.managesys.permissions.entity.SysUserEntity;

@Repository
public interface IPermisManageDao {
    /**
     * 根据用户查询其对应的权限集合</br>
     * 包括菜单和授权动作
     * @param entity
     * @return
     */
	List<SysMenuEntity> findUserPermissions(SysUserEntity entity);
   
	List getRoleByPage();

	List<SysMenuEntity> findAllMenus();
    /**
     * 根据非空字段查询数据
     * @param entity
     * @return
     */
	SysRoleEntity getSysRoleByFile(SysRoleEntity entity);
    /**
     * 插入角色对象
     * @param roleEntity
     */
	void insertRole(SysRoleEntity roleEntity);
    /**
     * 批量插入role_menu数据
     * @param list
     */
	void insertsRoleMenu(List<SysRoleMenuEntity> list);
    /**
     * 根据角色查询其所拥有的菜单集合
     * @param entity
     * @return
     */
	List<SysMenuEntity> findRolePermissions(SysRoleEntity entity);
    /**
     * 根据非空字段删除【角色--菜单】表数据
     * @param roleMenuEntity
     */
	void delRoleMenuByFile(SysRoleMenuEntity roleMenuEntity);
    /**
     * 根据id修改roel表非空字段</br>
     * id主键不在修改范围
     * @param roleEntity
     */
	void updateRoleById(SysRoleEntity roleEntity);
    /**
     * 根据id删除角色
     * @param entity
     */
	void delRoleById(SysRoleEntity entity);
    /**
     * 获取系统所有的角色
     */
	List getAllSysRole();

}
