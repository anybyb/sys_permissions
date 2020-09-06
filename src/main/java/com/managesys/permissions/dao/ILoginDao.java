package com.managesys.permissions.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.managesys.permissions.entity.SysUserEntity;

@Repository
public interface ILoginDao {
    /**
     * 根据用户名查询用户
     * @param sysUserEntity
     * @return
     */
	SysUserEntity findByUserName(SysUserEntity sysUserEntity);
    /**
     * 获取用户的所有的权限集合
     * @param entity
     * @return
     */
	List findUserPermissions(SysUserEntity entity);

}
