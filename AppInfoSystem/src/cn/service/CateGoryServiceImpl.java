package cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.CateGoryMapper;
import cn.pojo.app_category;
import cn.pojo.data_dictionary;
<<<<<<< HEAD


=======
>>>>>>> 5ec4319b840af750eda42ee330f74cf2aa44af0a
@Service("cateGoryService")
public class CateGoryServiceImpl implements CateGoryService{
	@Autowired
	 CateGoryMapper catecorymapper;
	
	
	

	@Override
	public List<data_dictionary> getpingtaiByName() {
		
		return catecorymapper.getpingtaiByName();
	}




	@Override
	public List<app_category> getCategory(int parentId) {
		return catecorymapper.getCategory(parentId);
	}
	@Override
	public List<data_dictionary> getpingtaiByName() {
		
		return catecorymapper.getpingtaiByName();
	}
}
	


