package com.managesys.permissions.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basic.common.utils.MD5Util;
import com.managesys.permissions.dao.ISysUserDao;
import com.managesys.permissions.entity.SysUserDto;
import com.managesys.permissions.entity.SysUserEntity;
import com.managesys.permissions.entity.SysUserRoleEntity;
import com.managesys.permissions.entity.SysUserSearch;
import com.managesys.permissions.service.ISysUserService;
import com.managesys.permissions.utils.SysConstant;



@Service("sysUserService")
public class SysUserServiceImpl  implements ISysUserService{
    @Resource
    private ISysUserDao sysUserDao;
   
	public List getSysUserByPage(SysUserSearch sysUserSearch) {
		
		return sysUserDao.getSysUserByPage(sysUserSearch);
	}

	
	public SysUserEntity getSysUserByFile(SysUserEntity entity) {
		
		return sysUserDao.getSysUserByFile(entity);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void addSysUser(SysUserDto sysUserDto) {
		 SysUserEntity sysUserEntity = new  SysUserEntity();
		 BeanUtils.copyProperties(sysUserDto, sysUserEntity);
		 sysUserEntity.setPassword(MD5Util.encode(sysUserDto.getPassword()).toLowerCase());
		 sysUserEntity.setStatus(SysConstant.DATA_VALID);
		 sysUserEntity.setCreateTime(new Date());
		 sysUserDao.addSysUser(sysUserEntity);
		 //鏄惁鏈夎鑹�
		 if(sysUserDto.getRoleId() != -1){
			 SysUserRoleEntity  sysUserRoleEntity = new  SysUserRoleEntity();
			 sysUserRoleEntity.setRid(sysUserDto.getRoleId());
			 sysUserRoleEntity.setUid(sysUserEntity.getId());
			 sysUserDao.addSysUserRole(sysUserRoleEntity);
		 }
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void updateSysUser(SysUserDto sysUserDto) {
		 SysUserEntity sysUserEntity = new  SysUserEntity();
		 sysUserEntity.setCity(sysUserDto.getCity());
		 sysUserEntity.setId(sysUserDto.getId());
		 sysUserEntity.setName(sysUserDto.getName());
		 sysUserDao.updateSysUserById(sysUserEntity);
		 //鍏堝垹闄�
		 SysUserRoleEntity  sysUserRoleEntity = new  SysUserRoleEntity();
		 sysUserRoleEntity.setUid(sysUserEntity.getId());
		 sysUserDao.deleteSysUserRoleByFile(sysUserRoleEntity);
		 //鍐嶆彃鍏�
		 sysUserRoleEntity.setRid(sysUserDto.getRoleId());
		 sysUserRoleEntity.setUid(sysUserEntity.getId());
		 sysUserDao.addSysUserRole(sysUserRoleEntity);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void deleteSysUser(SysUserDto sysUserDto) {
		 SysUserEntity sysUserEntity = new  SysUserEntity();
		 sysUserEntity.setId(sysUserDto.getId());
		 sysUserEntity.setStatus(SysConstant.DATA_INVALID);
		 sysUserDao.updateSysUserById(sysUserEntity);
		 //鍒犻櫎鍏宠仈鐨勮鑹�
		 SysUserRoleEntity  sysUserRoleEntity = new  SysUserRoleEntity();
		 sysUserRoleEntity.setUid(sysUserEntity.getId());
		 sysUserDao.deleteSysUserRoleByFile(sysUserRoleEntity);
	}

	
	public void updateSysUserPwd(SysUserDto sysUser) {
		 SysUserEntity sysUserEntity = new  SysUserEntity();
		 sysUserEntity.setId(sysUser.getId());
		 sysUserEntity.setPassword(sysUser.getPassword());
		 sysUserDao.updateSysUserById(sysUserEntity);
	}
	

	
}
