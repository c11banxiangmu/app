<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻中国</title>

<link href="css/read.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"src="ckeditor/ckeditor.js" ></script>
<script type="text/javascript">
	function check() {
		var cauthor = document.getElementById("cauthor");
		var content = document.getElementById("ccontent");
		if (cauthor.value == "") {
			alert("用户名不能为空！！");
			return false;
		} else if (content.value == "") {
			alert("评论内容不能为空！！");
			return false;
		}
		return true;
	}
		/* $(function(){
 		function show(){		
 			 $.ajax({
 			"url":"NewsServlet",
 			"type":"post",
 			"dataType":"json",
 			"success":showStu
 			}); 
 		}	
 		function showStu(data){	
 			$(data).each(function(){
 			$("#a").append(this.name+"-"+this.age+"<br/>");
 			});
 		}
 		show();
 	});  */
</script>
</head>
<body>
	<div id="header">
		<div id="top_login">
			<label> 登录名 </label> <input type="text" id="uname" value=""
				class="login_input" /> <label> 密&#160;&#160;码 </label> <input
				type="password" id="upwd" value="" class="login_input" /> <input
				type="button" class="login_sub" value="登录" onclick="login()" /> <label
				id="error"> </label> <a href="index.jsp" class="login_link">返回首页</a>
			<img src="images/friend_logo.gif" alt="Google" id="friend_logo" />
		</div>
		<div id="nav">
			<div id="logo">
				<img src="images/logo.jpg" alt="新闻中国" />
			</div>
			<div id="a_b01">
				<img src="images/a_b01.gif" alt="" />
			</div>
			<!--mainnav end-->
		</div>
	</div>
	<div id="container">
		<%@ include file="../index-elements/index_sidebar.jsp"%>
		<div class="main">
			<div class="class_type">
				<img src="images/class_type.gif" alt="新闻中心" />
			</div>
			<div class="content">
				<ul class="classlist">
					<table width="90%">
						<tr>
							<td colspan="2" align="center">${news.ntitle }</td>
						</tr>
						<tr>
							<td align="right">作者：${news.nauthor }</td>
							<td align="center">${news.ncreatedate}</td>
						</tr>
						<tr>
							<td colspan="2">${news.ncontent }</td>
						</tr>
					</table>
				</ul>
				<ul class="classlist">
					<c:choose>
						<c:when test="${empty news.comments }">
							<tr>
							<hr />
								<td>暂无评论！
									<hr />
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<table>
								<c:forEach items="${news.comments }" var="comment">
									<tr>
										<td>留言人：</td>
										<td>${comment.cauthor}</td>
										<td>IP：</td>
										<td>${comment.cip}</td>
										<td>留言时间：</td>
										<td><fmt:formatDate value="${comment.cdate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>
									<tr>
										<td colspan="6">${comment.ccontent}</td>
									</tr>
								</c:forEach>
							</table>
						</c:otherwise>
					</c:choose>
				</ul>
				<ul class="classlist">
				<p>评论</p>
				<form action="NewsServlet?type=addcom&nid=${news.nid}" method="post">
				<p>用户名：
				<c:choose>
				<c:when test="${not empty sessionScope.admin }">
				 <input id="cauthor" name="cauthor" value="${sessionScope.admin}" readonly/>
				</c:when>
				<c:otherwise>
				 <input id="cauthor" name="cauthor" value="这家伙很懒，什么也没有留下" size="30"/>
				</c:otherwise>
				</c:choose>
				
				IP: <input name="cip" id="cip" value="${pageContext.request.remoteAddr}" readonly/>
				</p>
				
				<textarea rows="10" cols="80" name="ccontent"></textarea>
				<script type="text/javascript">CKEDITOR.replace('ccontent');</script>
				<p><input type="submit" value="提交"/></p>
				</form>
				</ul>
			</div>
		</div>
	</div>
	<%--
    request.removeAttribute("news_view");
    request.removeAttribute("comments_view");
--%>
	<%@ include file="../index-elements/index_bottom.html"%>
</body>
</html>
