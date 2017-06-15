package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.app_category;
import cn.pojo.data_dictionary;

public interface CateGoryMapper {
<<<<<<< HEAD
	public List<app_category> getCategoryOne(@Param("parentId")int parentId);
	public List<app_category> getCategoryTow(int parentId);
	public List<app_category> getCategoryThree(@Param("parentId")int parentId);
	//平台
		public List<data_dictionary>getpingtaiByName();

=======
	public List<app_category> getCategory(@Param("parentId")int parentId);
	
	//平台
	public List<data_dictionary>getpingtaiByName();
	
>>>>>>> 5ec4319b840af750eda42ee330f74cf2aa44af0a
}
