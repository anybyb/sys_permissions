package com.managesys.permissions.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.managesys.permissions.dao.IPermisManageDao;
import com.managesys.permissions.dao.ISysMenuDao;
import com.managesys.permissions.entity.SysMenuEntity;
import com.managesys.permissions.entity.SysMenuListVo;
import com.managesys.permissions.entity.SysRoleMenuEntity;
import com.managesys.permissions.service.ISysMenuService;

@Service("sysMenuService")
public class SysMenuServiceImpl  implements ISysMenuService{
    @Resource
    private ISysMenuDao  sysMenuDao;
    @Resource
    private IPermisManageDao  permisManageDao;
	public List getTreeMenus() {
		return  getMemnus(0);//第一节点pid是0
   }
	/**
	 * 递归菜单树
	 * @param pid
	 * @return
	 */
	private  List  getMemnus(int pid){
		 List<SysMenuEntity> list = sysMenuDao.getTreeMenus(pid);
		 Iterator<SysMenuEntity> iterator = list.iterator();
		 List<SysMenuListVo> sysMenuListVoList = new ArrayList<SysMenuListVo>();
		 while (iterator.hasNext()) {
			     SysMenuListVo menuListVo = new SysMenuListVo();
				 SysMenuEntity sysMenuEntity = (SysMenuEntity) iterator.next();
				 menuListVo.setId(sysMenuEntity.getId());
				 menuListVo.setText(sysMenuEntity.getName());
				 menuListVo.setChildren(getMemnus(sysMenuEntity.getId()));
				 menuListVo.setPath(sysMenuEntity.getPath());
				 menuListVo.setPid(sysMenuEntity.getId());
				 System.out.println(sysMenuEntity.getName());
				 sysMenuListVoList.add(menuListVo);
		 }
		return sysMenuListVoList;
	  }
	
	public SysMenuEntity getSysMenuByFile(SysMenuEntity entity) {
		return sysMenuDao.getSysMenuByFile(entity);
	  }
   	
	public void addMenu(SysMenuEntity entity) {
		sysMenuDao.addMenu(entity);
		
	 }
	
	public void updateMenu(SysMenuEntity entity) {
		sysMenuDao.updateMenu(entity);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void deleteMenu(Integer id) {
		//删除该数据
		sysMenuDao.deleteMenuById(id);
		//删除该数据的子数据
		sysMenuDao.deleteMenuByPid(id);
		//删除关联表数据
		SysRoleMenuEntity roleMenuEntity = new SysRoleMenuEntity();
		roleMenuEntity.setMid(id);
		permisManageDao.delRoleMenuByFile(roleMenuEntity);
	}
	
	public void updateMenuName(SysMenuEntity entity) {
		sysMenuDao.updateMenuName(entity);
	}
	
	
	public SysMenuEntity getMenuByPath(String  menuPath){
	   return sysMenuDao.getMenuByPath(menuPath);
	}
	
	
}
