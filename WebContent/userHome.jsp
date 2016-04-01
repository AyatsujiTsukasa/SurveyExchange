<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js'></script>
<link rel="stylesheet" type="text/css" href="/SurveyExchange/stylesheet.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 
String name = session.getAttribute("name").toString();
String pwd = session.getAttribute("pwd").toString();
String credit = session.getAttribute("credit").toString();
String email = session.getAttribute("email").toString();
String id = session.getAttribute("id").toString();
%>
<title>Welcome <%=name %></title>
</head>
<body>
<form id=ViewMySurveys method="post" action="mySurveys"><input name="ownerId" value=<%=id %> type="hidden" /></form>
<p>
<span class="welcomeMessage">Hello, <%=name %>!</span>
<span class="sidebar">
	<a onclick="document.getElementById('ViewMySurveys').submit();"><span class="sidebarOptions">My Surveys</span></a> |
	<a href="/SurveyExchange/newSurvey.jsp"><span class="sidebarOptions">New Survey</span></a> |
	<span class="credit">Credit: <%=credit %></span>
</span>
</p>
<div class='homeDiv' id='selfFeed'>
    <h2 class='feedTitle'>New Responses</h2>
    <div class='newResponseList'>
        <%=session.getAttribute("selfFeed") %>
    </div>
</div>
<div class='homeDiv' id='otherFeed'>
    <h2 class='feedTitle'>Surveys For You</h2>
    <div class='SurveyFeed'>
        <%=session.getAttribute("otherFeed") %>
    </div>
</div>

<script type='text/javascript' src='/SurveyExchange/adminUserHome.js'></script>
</body>
</html>