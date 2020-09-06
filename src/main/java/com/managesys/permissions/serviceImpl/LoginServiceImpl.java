package com.managesys.permissions.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.basic.common.utils.MD5Util;
import com.managesys.permissions.dao.ILoginDao;
import com.managesys.permissions.entity.SysUserEntity;
import com.managesys.permissions.service.ILoginService;
import com.managesys.permissions.service.IPermisManageServcie;
import com.managesys.permissions.utils.SysConstant;


@Service("loginService")
public class LoginServiceImpl  implements ILoginService{
   @Resource
    private ILoginDao  loginDao;
   @Resource
    private IPermisManageServcie permisServcie;
	public Map<String, Object> login(SysUserEntity sysUserEntity) {
		Map<String, Object> map =new HashMap<String, Object>();
		sysUserEntity.setStatus(SysConstant.DATA_VALID);
		SysUserEntity  entity = loginDao.findByUserName(sysUserEntity);
		if(null == entity){
			//鐢ㄦ埛涓嶅瓨鍦�
			map.put("status", "0");
			map.put("message", "鐢ㄦ埛鍚嶄笉瀛樺湪");
		}else if(!entity.getPassword().equals(MD5Util.encode(sysUserEntity.getPassword()).toLowerCase())){
			//瀵嗙爜涓嶆纭�
			map.put("status", "0");
			map.put("message", "鐢ㄦ埛瀵嗙爜涓嶆纭�");
		}else{
			//姝ｇ‘鑾峰彇鍏朵笅闈㈢殑鎵�鏈夋潈闄愰泦鍚�
		   map.put("status", "1");
		   map.put("message", "鐧诲綍鎴愬姛");
		   map.put("user", entity);
		   Map<String, Object>   map1 =   permisServcie.getUSerMenus(entity.getId());
		   map.put("paths", map1.get("paths"));
		   map.put("menuList", map1.get("menuList"));
		}
		return map;
	}
	
	
    
}
