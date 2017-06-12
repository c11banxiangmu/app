package cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.App_infoMapper;
import cn.pojo.app_info;
@Service("app_infoService")
public class App_infoServiceImpl implements App_infoService {
	@Autowired
	App_infoMapper app_infomapper;
	

	public void setApp_infomapper(App_infoMapper app_infomapper) {
		this.app_infomapper = app_infomapper;
	}

	//查询app所有信息
	@Override
	public List<app_info> getAllApp(String softwareName, int status,
			int flatformId) {
		
		return app_infomapper.getAppInfo(softwareName, status, flatformId);
	}
	
}