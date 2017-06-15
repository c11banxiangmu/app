package cn.service;

import java.util.List;



import cn.pojo.app_category;
import cn.pojo.data_dictionary;

public interface CateGoryService {

	public List<app_category> getcategory1(int parentId);
	public List<app_category> getCategory2(int parentId);
	public List<app_category> getCategory3(int parentId);
	//平台
		public List<data_dictionary>getpingtaiByName();

	
	
	
}
