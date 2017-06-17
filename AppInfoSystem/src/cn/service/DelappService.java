package cn.service;

import cn.pojo.app_info;

public interface DelappService {
	public boolean delApp(int id);
	public app_info getAppById(int id);
	public boolean updateApp(app_info appInfo);
}
