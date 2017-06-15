package cn.controller;




import java.io.File;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;




import cn.pojo.app_category;
import cn.pojo.app_info;
import cn.pojo.data_dictionary;
import cn.service.App_infoService;
import cn.service.CateGoryService;
import cn.service.Data_dictionaryService;

@RequestMapping("/dev")
@Controller
public class App_infoController {
	@Autowired 
	App_infoService app_infoService;
	@Autowired
	Data_dictionaryService data_dictionaryService;
	@Autowired
	CateGoryService cateGoryService;
	//加载
	@RequestMapping(value="/appinfoadd.html",method=RequestMethod.GET)
	public String addAPP(){
		return "developer/appinfoadd";
	}
	//添加      上传
	@RequestMapping(value="/appinfoadd.html",method=RequestMethod.POST)
	public String addAPP(app_info appinfo,HttpSession session,HttpServletRequest request,
		@RequestParam(value ="a_logoPicPath", required = false) MultipartFile attach){
		
		
			String idPicPath = null;
			//判断文件是否为空
			if(!attach.isEmpty()){
				String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");			
				String oldFileName = attach.getOriginalFilename();//原文件名		
				String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀    	       
				int filesize = 500000;
				
		        if(attach.getSize() >  filesize){//上传大小不得超过 500k
	          	request.setAttribute("uploadFileError", " * 上传大小不得超过 500k");
		        	return "useradd";
	          }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
	          		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
	          	String fileName = System.currentTimeMillis()+"_Personal.jpg";  
	            
	              File targetFile = new File(path, fileName);  
	              if(!targetFile.exists()){  
	                  targetFile.mkdirs();  
	              }  
	              //保存  
	              try {  
	              	attach.transferTo(targetFile);  
	              } catch (Exception e) {  
	                  e.printStackTrace();  
	                  request.setAttribute("uploadFileError", " * 上传失败！");
	                  return "useradd";
	              }  
	              idPicPath = path+File.separator+fileName;
	          }else{
	          	request.setAttribute("uploadFileError", " * 上传图片格式不正确");
	          	return "useradd";
	          }
			}
		//添加	
			appinfo.setStatus(1);
		if(app_infoService.addAPP(appinfo)){
			return "redirect:/user/appinfolist.html";
		}else{
			return "developer/appinfoadd";
		}
		
		
	}
	//平台
	@RequestMapping(value="/pingtai",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object categorylevellist(){				
		List<data_dictionary>  cate  = cateGoryService.getpingtaiByName();
		return JSONArray.toJSONString(cate);
	}
	
	
	//三級
	@RequestMapping(value="/categoryLevel1",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object categorylevellist3(String pid){	
			if(pid==null||pid.equals("")){
				pid="0";
			}
		
		List<app_category>  cate  = cateGoryService.getCategory2(Integer.parseInt(pid));
		return JSONArray.toJSONString(cate);
	}
	
	@RequestMapping(value="/categoryLevel2",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object categorylevellist2(String pid){		
		List<app_category>  cate  = cateGoryService.getCategory2(Integer.parseInt(pid));
		return JSONArray.toJSONString(cate);
	}
	
	@RequestMapping(value="/categoryLevel3",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object categorylevellist1(String pid){
		List<app_category>  cate  = cateGoryService.getCategory2(Integer.parseInt(pid));
		return JSONArray.toJSONString(cate);
	}
	
	//验证
	@RequestMapping(value="/APKName")
	@ResponseBody
	public Object userCodeExits(String APKName){
		HashMap<String,String>result = new HashMap<String,String>();
		if(APKName.trim().length()==0){
			result.put("APKName", "exist");
		}else{
			boolean flag=app_infoService.getappByAPKname(APKName);
			if(flag){
				result.put("APKName", "exist");
			}else{
				result.put("APKName", "noexist");
			}
		}
		return JSONArray.toJSONString(result);
	}
}
