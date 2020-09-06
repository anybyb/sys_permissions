package com.managesys.permissions.service;

import java.util.Map;

import com.managesys.permissions.entity.SysUserEntity;

public interface ILoginService {
    /**
     * 用户登录，登录成功后返回该用户所有的的权限集合
     * @param sysUserEntity
     * @return
     */
	Map<String, Object> login(SysUserEntity sysUserEntity);

}
