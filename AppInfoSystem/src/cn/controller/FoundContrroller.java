package cn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.pojo.app_info;
import cn.pojo.app_version;
import cn.service.App_infoService;
import cn.service.VersionService;
@RequestMapping("/user")
@Controller
public class FoundContrroller {
	@Autowired 
	App_infoService app_infoService;
	@Autowired
	VersionService versionService;
	
	@RequestMapping(value="/appinfoview/{appinfoid}")
	public String foundApp(@PathVariable String appinfoid,HttpServletRequest request){
		if(appinfoid.trim().length()==0){
			return "redirect:/user/appinfolist.html";
		}
		app_info appinfo = app_infoService.getAppById(Integer.parseInt(appinfoid));
		request.setAttribute("appInfo", appinfo);
		
		List<app_version> version =versionService.getversion(Integer.parseInt(appinfoid));
		 request.setAttribute("appVersionList", version);
		return "developer/appinfoview";
		
		
	}
}
