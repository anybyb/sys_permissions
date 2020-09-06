package com.managesys.permissions.entity;

import java.util.Date;

public class SysUserEntity {
	private Integer id;
	private String name;
	private String password;
	private String city;
	private Date createTime;
	private Integer status;
    
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
