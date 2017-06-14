package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.app_version;

public interface VersionMapper {
	public List<app_version> getversion(@Param("id")int id);
}
