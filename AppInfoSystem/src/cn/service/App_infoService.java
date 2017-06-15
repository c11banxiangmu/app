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

	
<<<<<<< HEAD
	
	//添加
	public boolean addAPP(app_info appinfo);
	public boolean getappByAPKname(String APKName);

=======
	//添加
	public boolean addAPP(app_info appinfo);
	//根据名称查询APP信息
	public boolean getappByAPKname(String APKName);
>>>>>>> 5ec4319b840af750eda42ee330f74cf2aa44af0a
}
