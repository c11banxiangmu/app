package cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.CateGoryMapper;
import cn.pojo.app_category;
import cn.pojo.data_dictionary;


@Service("cateGoryService")
public class CateGoryServiceImpl implements CateGoryService{
	@Autowired
	 public CateGoryMapper catecorymapper;
	
	
	

	@Override
	public List<data_dictionary> getpingtaiByName() {
		
		return catecorymapper.getpingtaiByName();
	}




	@Override
	public List<app_category> getcategory1(int parentId) {
		// TODO 自动生成的方法存根
		return catecorymapper.getCategoryOne(parentId);
	}




	@Override
	public List<app_category> getCategory2(int parentId) {
		// TODO 自动生成的方法存根
		return catecorymapper.getCategoryTow(parentId);
	}




	@Override
	public List<app_category> getCategory3(int parentId) {
		// TODO 自动生成的方法存根
		return catecorymapper.getCategoryThree(parentId);
	}



}
	


