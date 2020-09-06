package com.managesys.permissions.dao;

import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;

import com.managesys.permissions.entity.SysUserEntity;
import com.managesys.permissions.entity.SysUserRoleEntity;
import com.managesys.permissions.entity.SysUserSearch;

@ResponseBody
public interface ISysUserDao {

	List getSysUserByPage(SysUserSearch sysUserSearch);
     /**
      * 根据非空字段查询对象
      * @param entity
      * @return
      */
	SysUserEntity getSysUserByFile(SysUserEntity entity);
    /**
     * 添加系统用户
     * @param sysUserEntity
     */
	void addSysUser(SysUserEntity sysUserEntity);
    /**
     * 添加用户-角色对应表
     * @param sysUserRoleEntity
     */
	void addSysUserRole(SysUserRoleEntity sysUserRoleEntity);
   /**
    * 修改用户根据id
    * @param sysUserEntity
    */
	void updateSysUserById(SysUserEntity sysUserEntity);
    /**
     * 物理删除用户_角色id
     * @param sysUserRoleEntity
     */
    void deleteSysUserRoleByFile(SysUserRoleEntity sysUserRoleEntity);
    /**
     * 修改用户关联的角色
     * @param sysUserRoleEntity
     */
	void updateSysUserRole(SysUserRoleEntity sysUserRoleEntity);



  

}
