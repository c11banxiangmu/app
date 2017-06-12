package org.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.news.entity.Comment;
import org.news.entity.News;

import org.news.service.CommentsService;
import org.news.service.NewsService;
import org.news.service.TopicsService;

import org.news.service.impl.CommentsServiceImpl;
import org.news.service.impl.NewsServiceImpl;
import org.news.service.impl.TopicsServiceImpl;

@WebServlet(name="NewsServlet",value="/NewsServlet")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 7679716260193021854L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
	
		String contextPath = request.getContextPath();
		String type = request.getParameter("type");
		NewsService newsService = new NewsServiceImpl();
		TopicsService topicService=new TopicsServiceImpl();
		CommentsService commentsService = new CommentsServiceImpl();
		Comment new_con = new Comment();
		 if(type.equals("addcom")){
			String nid_String = request.getParameter("nid");
			new_con.setCnid(Integer.parseInt(nid_String));//新闻ID
			//获的当前时间
			new_con.setCdate(new Date());
			//获取文本内容
			String ccontent_String = request.getParameter("ccontent");
			new_con.setCcontent(ccontent_String);
			//获取IP地址
			String cip_String = request.getParameter("cip");
			new_con.setCip(cip_String);
			//获取用户名
			String cauthor_String = request.getParameter("cauthor");
			if(cauthor_String.equals("这家伙很懒，什么也没有留下")){
				cauthor_String = "游客";
				new_con.setCauthor(cauthor_String);
			}else{
				new_con.setCauthor(cauthor_String);
			}
			try {
				int row = commentsService.addComment(new_con);
				 out.print("<script type=\"text/javascript\">");
                 out.print("alert(\"评论成功，点击确认返回原来页面\"); ");
                 out.print("location.href=\"" + contextPath
                         + "/NewsServlet?type=read&nid=" + nid_String + "\";");
                 out.print("</script>");
			} catch (SQLException e) {
				 out.print("<script type=\"text/javascript\">");
                 out.print("alert(\"评论添加失败！请联系管理员查找原因！点击确认返回原来页面\");");
                 out.print("location.href=\"" + contextPath
                         + "/NewsServlet?type=read&nid=" + nid_String + "\";");
                 out.print("</script>");
				e.printStackTrace();
			}
		}else if (type.equals("read")) {
			out.print("<script type='text/javascript'>alert('no');</script>");
			String nid = request.getParameter("nid");
			try {
				News news = newsService.findNewsByNid(Integer.parseInt(nid));
				news.setComments(commentsService.findCommentsByNid(Integer
						.parseInt(nid)));
				Map<Integer, Integer> topics = new HashMap<Integer, Integer>();
				topics.put(1, 5);
				topics.put(2, 5);
				topics.put(5, 5);

				List<List<News>> latests = newsService
						.findLatestNewsByTid(topics);
				request.setAttribute("news", news);
				request.setAttribute("list1", latests.get(0));// 国内
				request.setAttribute("list2", latests.get(1));// 国外
				request.setAttribute("list3", latests.get(2));// 国内
				request.getRequestDispatcher("/newspages/news_read.jsp")
						.forward(request, response);
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}

		} else if (type.equals("addnews")) { // 查找所有主题，准备添加新闻
            try {
				request.setAttribute("topics", topicService.findAllTopics());
				 request.getRequestDispatcher("/newspages/news_add.jsp")
                 	.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
           
		} else if (type.equals("")){}
		out.flush();
		out.close();
	}

}
