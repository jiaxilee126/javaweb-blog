package com.lee.qiniu.service;

import java.util.Set;

import com.lee.qiniu.entity.Admin;
import com.lee.qiniu.json.JsonResult;

/**
 * @Description: 后台接口
 * @author Jussi Lee
 * @date 2018年11月27日
 */
public interface IAdminService {
	JsonResult doLogin(String username, String password);
	Admin getByName(String username);
	
	/**
	 * @Description: 根据用户id获取所有的角色
	 * @param id
	 * @return Set<String>  
	 * @throws
	 * @author Jussi Lee
	 * @date 2018年11月27日
	 */
	Set<String> getRoles(Integer id);
	
	Set<String> getPermissions (Integer id);
	
	
}
