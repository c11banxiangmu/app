package cn.service;

import java.util.List;

import cn.pojo.app_info;

public interface App_infoService {
	public List<app_info> getAllApp(String softwareName,int status,int flatformId,
			int categoryLevel1,int categoryLevel2,int categoryLevel3,
			int curreIndex,int pageSize);
	public int getCountByConcent(String softwareName,int status,int flatformId,
			int categoryLevel1,int categoryLevel2,int categoryLevel3);
	public app_info getAppById(int id);

	

	
	//添加
	public boolean addAPP(app_info appinfo);
	public boolean getappByAPKname(String APKName);


}
