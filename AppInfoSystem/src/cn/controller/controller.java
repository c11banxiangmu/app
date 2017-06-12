package cn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import cn.pojo.app_info;
import cn.pojo.data_dictionary;
import cn.pojo.dev_user;
import cn.service.App_infoService;
import cn.service.Data_dictionaryService;
import cn.service.UserService;


@RequestMapping("/user")
@Controller
public class controller {
	@Autowired 
	UserService userService;
	@Autowired 
	App_infoService app_infoService;
	@Autowired
	Data_dictionaryService data_dictionaryService;
	
	//登陆
	@RequestMapping(value="/login.html",method=RequestMethod.GET)
	public String login(){
		return "devlogin";
	}
	//登陆
	@RequestMapping(value="/dologin.html",method=RequestMethod.POST)
	public String login(@Param("devCode")String devCode, @Param("devPassword")String devPassword,HttpSession session){
		

		dev_user user= userService.login(devCode, devPassword);
		if(user == null){
			session.setAttribute("error", "用户名或密码错误！");
			return "devlogin";
		}else{
			//登录成功 			
			session.setAttribute("devUserSession", user); 
			return "developer/main";
		}
	}
		
	//加载页面
	@RequestMapping(value="/applist.html",method=RequestMethod.GET)
	public String applist(){
		return "developer/appinfolist";
	}
	
	@RequestMapping(value="/applist.html",method=RequestMethod.POST)
	public String applist(HttpServletRequest request,
			@RequestParam(required=false)String querySoftwareName,
			@RequestParam(required=false)String queryStatus,
			@RequestParam(required=false)String queryFlatformId){
		
		List<data_dictionary> dictionary =data_dictionaryService.getdictionary();
		request.setAttribute("statusList", dictionary);
		request.setAttribute("flatFormList", dictionary);
		
		if(queryStatus==null){
			queryStatus="0";
		}
		if(queryFlatformId==null){
			queryFlatformId="0";
		}
		
		List<app_info> applist =app_infoService.getAllApp(querySoftwareName,Integer.parseInt(queryStatus),Integer.parseInt(queryFlatformId));
		request.setAttribute("appInfoList", applist);
		return "developer/appinfolist";
	}
}
