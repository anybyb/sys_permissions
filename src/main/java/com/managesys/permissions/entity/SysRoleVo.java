package com.managesys.permissions.entity;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class SysRoleVo extends SysRoleEntity {
  private  List<String> menusName;
  private  List<String> userName;
  private  String menusNames;
  private  String userNames;
   @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  public Date getCreateTime() {
	return createTime;
}

   public void setCreateTime(Date createTime) {
	this.createTime = createTime;
  }

  public String getMenusNames() {
	if(menusName.size()!=0){
		menusNames = menusName.toString();
	}
	return menusNames;
}

public void setMenusNames(String menusNames) {
	this.menusNames = menusNames;
}

public List<String> getMenusName() {
	return menusName;
 }

 public void setMenusName(List<String> menusName) {
	this.menusName = menusName;
 }
 public List<String> getUserName() {
	return userName;
}

public void setUserName(List<String> userName) {
	this.userName = userName;
}

public String getUserNames() {
	if(userName.size()!=0){
		userNames = userName.toString();
	}
	return userNames;
}

public void setUserNames(String userNames) {
	this.userNames = userNames;
}


}
