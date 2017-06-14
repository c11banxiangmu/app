package cn.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.app_info;

public interface App_infoService {
	public List<app_info> getAllApp(String softwareName,int status,int flatformId);
	
	//添加
	public boolean addAPP(app_info appinfo);
	//根据名称查询APP信息
	public boolean getappByAPKname(String APKName);
}
