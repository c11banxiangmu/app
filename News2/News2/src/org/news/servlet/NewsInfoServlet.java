package org.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.news.dao.TopicsDao;
import org.news.dao.impl.TopicsDaoImpl;
import org.news.entity.News;
import org.news.entity.Topic;
import org.news.service.NewsService;
import org.news.service.TopicsService;
import org.news.service.impl.NewsServiceImpl;
import org.news.service.impl.TopicsServiceImpl;

import com.alibaba.fastjson.JSONArray;


@WebServlet(name="NewsInfoServlet",value="/NewsInfoServlet")
public class NewsInfoServlet extends HttpServlet {

	NewsService newsService = new NewsServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();	
		String opr = request.getParameter("opr");
		String type = request.getParameter("type");
		if(opr.equals("guonei")){
			showNewIn( request, response , out);
		}else if(opr.equals("guowai")){
			showNewOut( request,response, out);
		}else if(opr.equals("yule")){
			showDisport(request,response,out);
		}else if(opr.equals("zhuti")){
			showMotive(request,response,out);
		}else if(type.equals("tid")){
			showCut(request,response,out);
		}
		
		out.flush();
		out.close();
	}

	public void showNewIn(HttpServletRequest request, HttpServletResponse response,PrintWriter out ){
		//获取国内新闻
		List<News> list_news =new ArrayList<News>();
		try {
		list_news=newsService.findLatestNewsByTid(1, 5);
		out.print(JSONArray.toJSON(list_news));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showNewOut(HttpServletRequest request, HttpServletResponse response,PrintWriter out ){
		List<News> list_news =new ArrayList<News>();
		try {
		list_news=newsService.findLatestNewsByTid(2, 5);
		out.print(JSONArray.toJSON(list_news));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得娱乐新闻集合
	 * @param request
	 * @param response
	 * @param out
	 */
	public void showDisport(HttpServletRequest request, HttpServletResponse response,PrintWriter out ){
		List<News> list_news =new ArrayList<News>();
		try {
		list_news=newsService.findLatestNewsByTid(5, 5);
		out.print(JSONArray.toJSON(list_news));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showMotive(HttpServletRequest request, HttpServletResponse response,PrintWriter out ){
		//获得所有的主题
		TopicsService topicsDao = new TopicsServiceImpl();
		List<Topic> list_news =new ArrayList<Topic>();
		try {
			list_news = topicsDao.findAllTopics();
			out.print(JSONArray.toJSON(list_news));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 主题切换
	 * @param request
	 * @param response
	 * @param out
	 */
	public void showCut(HttpServletRequest request, HttpServletResponse response,PrintWriter out ){
		NewsService newsService = new NewsServiceImpl();
		List<News> list_news =new ArrayList<News>();
		String nid_0 = request.getParameter("nid");
		int nid = Integer.parseInt(nid_0);
		try {
		list_news = newsService.findAllNewsByTid(nid);
		out.print(JSONArray.toJSON(list_news));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
