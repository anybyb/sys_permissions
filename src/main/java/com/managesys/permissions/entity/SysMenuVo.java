package com.managesys.permissions.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 系统菜单
 * @author wcc
 * 将权限组织分离出来</br>
 * 形成子父菜单</br>
 */
public class SysMenuVo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pid;
	private String name;
	private String path;
	private List<SysMenuVo> subMenu;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<SysMenuVo> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<SysMenuVo> subMenu) {
		this.subMenu = subMenu;
	}
}
