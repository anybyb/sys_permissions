package com.managesys.permissions.service;

import java.util.List;

import com.managesys.permissions.entity.SysMenuEntity;

public interface ISysMenuService {

	List getTreeMenus();

	SysMenuEntity getSysMenuByFile(SysMenuEntity entity);

	void addMenu(SysMenuEntity entity);

	void updateMenu(SysMenuEntity entity);
     /**
      * 删除菜单，以及其下面的子菜单，以及role-menu表中的关联数据
      * @param id
      */
	void deleteMenu(Integer id);
    /**
     * 修改用户菜单名字
     * @param entity
     */
	void updateMenuName(SysMenuEntity entity);

}
