package com.managesys.permissions.service;

import java.util.List;

import com.managesys.permissions.entity.SysUserDto;
import com.managesys.permissions.entity.SysUserEntity;
import com.managesys.permissions.entity.SysUserSearch;

public interface ISysUserService {
    /**
     * 分页查询用户列表
     * @param sysUserSearch
     * @return
     */
	List getSysUserByPage(SysUserSearch sysUserSearch);
  
	/**
	 * 根据非空字段获取系统用户
	 * @param entity
	 * @return
	 */
	SysUserEntity getSysUserByFile(SysUserEntity entity);
	/**
	 * 添加  
	 * @param sysUserEntity
	 */
	void addSysUser(SysUserDto sysUserDto);
	/**
	 * 修改
	 * @param sysUserDto
	 */
	void updateSysUser(SysUserDto sysUserDto);
	/**
	 * 删除系统用户
	 * @param sysUserDto
	 */
	void deleteSysUser(SysUserDto sysUserDto);
	/**
	 * 修改系统用户密码
	 * @param sysUser
	 */
	void updateSysUserPwd(SysUserDto sysUser);
	

}
