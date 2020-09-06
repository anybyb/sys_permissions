package com.managesys.permissions.entity;

import java.util.Date;

/*
 * 角色菜单对应表
 */
public class SysRoleMenuEntity {
	private Integer id;
	private Integer rid;
	private Integer mid;
	private Date createTime;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}

}
