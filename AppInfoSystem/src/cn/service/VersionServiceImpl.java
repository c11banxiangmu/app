package cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.VersionMapper;
import cn.pojo.app_version;
@Service("versionService")
public class VersionServiceImpl implements VersionService {
	@Autowired
	public VersionMapper versionmapper;
	
	public void setVersionmapper(VersionMapper versionmapper) {
		this.versionmapper = versionmapper;
	}

	@Override
	public List<app_version> getversion(int id) {
		
		return versionmapper.getversion(id);
	}

}
