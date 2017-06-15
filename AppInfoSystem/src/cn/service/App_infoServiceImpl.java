package cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.App_infoMapper;
import cn.pojo.app_info;
@Service("app_infoService")
public class App_infoServiceImpl implements App_infoService {
	@Autowired
	public App_infoMapper app_infomapper;
	

	public void setApp_infomapper(App_infoMapper app_infomapper) {
		this.app_infomapper = app_infomapper;
	}


	@Override
	public int getCountByConcent(String softwareName, int status, int flatformId,int categoryLevel1,int categoryLevel2,int categoryLevel3) {
		// TODO 自动生成的方法存根
		return app_infomapper.getCountByConcent(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3);
	}

	@Override
	public List<app_info> getAllApp(String softwareName, int status,
			int flatformId, int categoryLevel1, int categoryLevel2,
			int categoryLevel3, int curreIndex, int pageSize) {
		// TODO 自动生成的方法存根
		return app_infomapper.getAppInfo(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, (curreIndex-1)*pageSize, pageSize);
	}


	@Override
	public app_info getAppById(int id) {
		
		return app_infomapper.getAppById(id);
	}
	//添加
	@Override
	public boolean addAPP(app_info appinfo) {
		int row=app_infomapper.addAPP(appinfo);
		if(row==1){
			return true;
		}else{
			return false;
		}	
	}
	
	@Override
	public boolean getappByAPKname(String APKName) {
		app_info appinfo=app_infomapper.getappByAPKname(APKName);
		if(appinfo==null){
			return false;
		}else{
			return true;
		}
		
	}

	
}
