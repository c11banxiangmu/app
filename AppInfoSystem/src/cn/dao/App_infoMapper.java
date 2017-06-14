package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.app_info;

public interface App_infoMapper {
	
	//查询所有app信息
	public List<app_info> getAppInfo(@Param("softwareName")String softwareName,@Param("status")int status,@Param("flatformId")int flatformId);
	//添加
	public int addAPP(app_info appinfo);
	//根据名称查询app信息	
	public app_info getappByAPKname(@Param("APKName")String APKName);
	
	
	
}
