package cn.dao;

import org.apache.ibatis.annotations.Param;

import cn.pojo.app_info;

public interface DelappMapper {
	//删除
	public int delApp(@Param("id")int id);
	//查询列表
	public app_info getAppById(@Param("id")int id);
	
	//修改app信息
	public int updateApp(app_info appInfo);
	
}
