package com.managesys.permissions.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.managesys.permissions.entity.SysMenuEntity;

@Repository
public interface ISysMenuDao {

	List getTreeMenus(int  pid);

	SysMenuEntity getSysMenuByFile(SysMenuEntity entity);

	void addMenu(SysMenuEntity entity);

	void updateMenu(SysMenuEntity entity);

	void deleteMenuById(Integer id);

	void deleteMenuByPid(Integer id);

	void updateMenuName(SysMenuEntity entity);
    /**
     * 根据菜单路径查询菜单的id 我们保证系统中的菜单的路径是唯一
     * 但是其是一个业务字段，可能会被修改。
     * @param menuPath
     * @return
     */
	SysMenuEntity getMenuByPath(String menuPath);
    /**
     * 根据pid查询集合
     * @param pid
     * @return
     */
	List getMenusByPid(int pid);

}
