package com.managesys.permissions.entity;

import java.util.List;

/**
 * 菜单管理处，列表显示的对象
 * easyui  treegrid
 * @author wcc
 *
 */
public class SysMenuListVo {
	private Integer id;
	private String text;
	private String state;
	private List<SysMenuListVo>  children;
	private String path;
	private Integer pid;
	

	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<SysMenuListVo> getChildren() {
		return children;
	}
	public void setChildren(List<SysMenuListVo> children) {
		this.children = children;
	}

}
