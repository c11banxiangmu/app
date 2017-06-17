package cn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.DelappMapper;
import cn.pojo.app_info;
@Service("delappService")
public class DelappServiceImpl implements DelappService {
	@Autowired
	public DelappMapper delappmapper;
	//删除
	@Override
	public boolean delApp(int id) {
		int	app=delappmapper.delApp(id);
		if(app!=1){
			return false;
		}else{
			return true;
		}	
		}
	@Override
	public app_info getAppById(int id) {
		// TODO 自动生成的方法存根
		return delappmapper.getAppById(id);
	}
	
	//修改
	@Override
	public boolean updateApp(app_info appInfo) {
		int	app=delappmapper.updateApp(appInfo);
		if(app!=1){
			return false;
		}else{
			return true;
		}	
	}
	}


