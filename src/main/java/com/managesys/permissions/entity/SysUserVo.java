package com.managesys.permissions.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SysUserVo extends SysUserEntity {
	private SysRoleEntity roles;

	public SysRoleEntity getRoles() {
		return roles;
	}

	public void setRoles(SysRoleEntity roles) {
		this.roles = roles;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;



	public Date getCreateTime() {
	
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
