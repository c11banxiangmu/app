package cn.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import cn.pojo.app_info;
import cn.service.App_infoService;

@RequestMapping("/dev")
@Controller
public class App_infoController {
	@Autowired 
	App_infoService app_infoService;
	//jiazai
	@RequestMapping(value="/appinfoadd.html",method=RequestMethod.GET)
	public String addAPP(){
		return "developer/appinfoadd";
	}
	//tianjia
	@RequestMapping(value="/appinfoadd.html",method=RequestMethod.POST)
	public String addAPP(@ModelAttribute app_info appinfo){
		if(app_infoService.addAPP(appinfo)){
			return "redirect:/user/appinfolist.html";
		}else{
			return "developer/appinfoadd";
		}
		
	}
}
