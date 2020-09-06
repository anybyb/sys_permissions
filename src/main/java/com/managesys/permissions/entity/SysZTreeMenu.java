package com.managesys.permissions.entity;

/**
 * 角色授权时展示的tree
 * @author Administrator
 *
 */
public class SysZTreeMenu{
	private Integer id;
	private Integer pId;//I 必须大写
	private boolean open = false;//节点是否打开
	private boolean checked = false;//节点是否选中
	private boolean chkDisabled = false;//节点是否可选,默认不禁用
	private String name;
	private String pathDIY;//自定义属性
	public String getPathDIY() {
		return pathDIY;
	}
	public void setPathDIY(String pathDIY) {
		this.pathDIY = pathDIY;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
    
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

}
