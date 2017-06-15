package cn.service;

import java.util.List;



import cn.pojo.app_category;
import cn.pojo.data_dictionary;

public interface CateGoryService {
<<<<<<< HEAD
	public List<app_category> getcategory1(int parentId);
	public List<app_category> getCategory2(int parentId);
	public List<app_category> getCategory3(int parentId);
	//平台
		public List<data_dictionary>getpingtaiByName();
=======
	public List<app_category> getCategory(int parentId);
	
	
	//平台
	public List<data_dictionary>getpingtaiByName();
>>>>>>> 5ec4319b840af750eda42ee330f74cf2aa44af0a
}
