package cn.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;



import cn.pojo.app_category;
import cn.pojo.app_info;
import cn.pojo.data_dictionary;
import cn.pojo.dev_user;
import cn.service.App_infoService;
import cn.service.CateGoryService;
import cn.service.DelappService;

@RequestMapping("/dev")
@Controller
public class DelAppController {
	private Logger logger = Logger.getLogger("DelAppController.class");
	@Autowired
	DelappService delappService;
	@Autowired 
	App_infoService app_infoService;
	@Autowired
	CateGoryService cateGoryService;
	//删除
	@RequestMapping(value="/delapp")
	@ResponseBody
	public Object delapp( String id){
		HashMap<String,String> result = new HashMap<String,String>();
		app_info app =delappService.getAppById(Integer.parseInt(id));
		if(app!=null){
			boolean flag =delappService.delApp(Integer.parseInt(id));
			if(flag){
				result.put("delResult", "true");
			}else{
				result.put("delResult", "false");
			}
		}else{
			result.put("delResult", "notexist");
		}
		return JSONArray.toJSONString(result);		
	}
	
	//修改
	
	@RequestMapping(value = "/appinfomodify")
	public String updateAppinfo(Integer id, Model model, HttpSession session) {
		app_info appInfo = app_infoService.getAppById(id);
		session.setAttribute("appInfo", appInfo);
		model.addAttribute("pid", id);
		return "developer/appinfomodify";
	}
	//平台
	@RequestMapping(value="/datadictionarylist",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object categorylevellist(){				
		List<data_dictionary>  cate  = cateGoryService.getpingtaiByName();
		return JSONArray.toJSONString(cate);
	}
	
	// 一二三级分类
		@RequestMapping(value = "/categoryLevelAll", produces = "application/json;charset=utf-8")
		@ResponseBody
		public String categoryLevelAll(String pid) {
			if (pid == null || pid.equals("")) {
				pid = "0";
			}
			List<app_category> categoryList = cateGoryService.getCategory2(Integer.parseInt(pid));	
			return JSONArray.toJSONString(categoryList);
		}
		//图片修改
		@RequestMapping(value = "/delfile", produces = "application/json;charset=utf-8")
		@ResponseBody
		public Object deletephoto() {
			HashMap<String, String> resutlt = new HashMap<String, String>();
			resutlt.put("result", "success");
			return JSONArray.toJSONString(resutlt);
		}
		
		//
		@RequestMapping(value = "/appinfomodifysave", method = RequestMethod.POST)
		public String updateApp(
				app_info appInfo,
				HttpSession session,
				HttpServletRequest request,
				@RequestParam(value = "attach", required = false) MultipartFile attach) {
			String hid_logoPicPath = request.getParameter("hid_logoPicPath");
			System.out.println(hid_logoPicPath);
			String logoLocPath = null;
			// 判断文件是否为空
			if (!attach.isEmpty()) {
				String path = request.getSession().getServletContext()
						.getRealPath("statics" + File.separator + "uploadfiles");
				String oldFileName = attach.getOriginalFilename();// 原文件名
				String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
				int filesize = 512000;

				if (attach.getSize() > filesize) {// 上传大小不得超过 500k
					request.setAttribute("uploadFileError", " * 上传大小不得超过 500k");
					return "useradd";
				} else if (prefix.equalsIgnoreCase("jpg")
						|| prefix.equalsIgnoreCase("png")
						|| prefix.equalsIgnoreCase("jpeg")
						|| prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
					String fileName = System.currentTimeMillis()
							+ "_Personal.jpg";
					logger.debug("new fileName======== " + attach.getName());
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					try {
						attach.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("uploadFileError", " * 上传失败！");
						return "developer/appinfomodify";
					}
					logoLocPath = path + File.separator + fileName;
				} else {
					request.setAttribute("uploadFileError", " * 上传图片格式不正确");
					return "developer/appinfomodify";
				}
			}
			int devId = ((dev_user)session.getAttribute("devUserSession")).getId();
			appInfo.setDevId(devId);
			appInfo.setModifyBy(devId);
			appInfo.setModifyDate(new Date());
			appInfo.setLogoPicPath(hid_logoPicPath);
			appInfo.setLogoLocPath(logoLocPath);
			boolean flag = delappService.updateApp(appInfo);
			if (flag) {
				logger.info("修改成功");
				return "redirect:/user/appinfolist.html";
			} else {
				logger.info("修改失败");
				return "false";
			}
		}
	
}
