<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js'></script>
<link rel="stylesheet" type="text/css" href="/SurveyExchange/stylesheet.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=session.getAttribute("title") %></title>
</head>
<body>
	<div id='main'>
		<%=session.getAttribute("title") %>?=?<%=session.getAttribute("desc") %>?=?<%=session.getAttribute("surveyString") %>?=?<%=session.getAttribute("author") %>?=?<%=session.getAttribute("email") %>?=?<%=session.getAttribute("participatorId") %>
	</div>
<script type='text/javascript' src='/SurveyExchange/doSurvey.js'></script>
</body>
</html>