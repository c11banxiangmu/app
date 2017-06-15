package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.app_info;

public interface App_infoMapper {
	
	//查询所有app信息


	public List<app_info> getAppInfo(@Param("softwareName")String softwareName,@Param("status")int status,@Param("flatformId")int flatformId,
			@Param("categoryLevel1")int categoryLevel1,@Param("categoryLevel2")int categoryLevel2,@Param("categoryLevel3")int categoryLevel3,
			@Param("index")int index,@Param("pageSize")int pageSize);
	public int getCountByConcent(@Param("softwareName")String softwareName,@Param("status")int status,@Param("flatformId")int flatformId,
			@Param("categoryLevel1")int categoryLevel1,@Param("categoryLevel2")int categoryLevel2,@Param("categoryLevel3")int categoryLevel3);
	public app_info getAppById(@Param("id")int id);

	
	
	public int addAPP(app_info appinfo);
	//根据名称查询app信息	
		public app_info getappByAPKname(@Param("APKName")String APKName);

	public List<app_info> getAppInfo(@Param("softwareName")String softwareName,@Param("status")int status,@Param("flatformId")int flatformId);
	//添加
	
	

	

}
