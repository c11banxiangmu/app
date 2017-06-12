package cn.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import cn.pojo.dev_user;
import cn.service.UserService;


@RequestMapping("/user")
@Controller
public class controller {
	@Autowired 
	UserService userService;
	
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
		
		
}
