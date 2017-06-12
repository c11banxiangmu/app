<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<link href="../css/admin.css" rel="stylesheet" type="text/css" />
<div id="header">
  <div id="welcome">欢迎使用新闻管理系统！</div>
  <div id="nav">
    <div id="logo"><img src="../images/logo.jpg" alt="新闻中国" /></div>
    <div id="a_b01"><img src="../images/a_b01.gif" alt="" /></div>
  </div>
</div>
<div id="admin_bar">
  <div id="status">管理员： <%=session.getAttribute("login")%> &#160;&#160;&#160;&#160; <a href="out.jsp">login out</a></div>
  <div id="channel"> </div>
</div>
