<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
 	<script type="text/javascript">
 	//国内新闻
 	$(function(){
 		function show1(){	
 			$.getJSON("NewsInfoServlet?opr=guonei",showguonei);
 		}	
 		function showguonei(data){
 			 $(data).each(function(){
 			$("#side_list").append("<li> <a href='NewsServlet?type=read&nid="+this.nid+"'><b>"+this.ntitle+"</b></a> </li>");
 			}); 
 		}
 		//国际新闻
 		function show2(){	
 			$.getJSON("NewsInfoServlet?opr=guowai",showguoji);
 		}	
 		function showguoji(data){
 			$(data).each(function(){
 			$("#side_list1").append("<li> <a href='NewsServlet?type=read&nid="+this.nid+"'><b>"+this.ntitle+"</b></a> </li>");
 			});
 			
 		}
 		//娱乐新闻
 		function show3(){	
 			$.getJSON("NewsInfoServlet?opr=yule",showyule);
 		}	
 		function showyule(data){
 			$(data).each(function(){
 			$("#side_list2").append("<li> <a href='NewsServlet?type=read&nid="+this.nid+"'><b>"+this.ntitle+"</b></a> </li>");
 			});
 			
 		}
 		show1();
 		show2();
 		show3();
 	}); 
 	
 	</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
  <div class="sidebar">
    <h1> <img src="images/title_1.gif" alt="国内新闻" /> </h1>
    <div class="side_list">
      <ul id="side_list" >
      </ul>
    </div>
    <h1> <img src="images/title_2.gif" alt="国际新闻" /> </h1>
    <div class="side_list">
      <ul id="side_list1">
      	
      </ul>
    </div>
    <h1> <img src="images/title_3.gif" alt="娱乐新闻" /> </h1>
    <div class="side_list">
      <ul id="side_list2">
      	
      </ul>
    </div>
  </div>
<%--
	request.removeAttribute("news_in_topic");
--%>
