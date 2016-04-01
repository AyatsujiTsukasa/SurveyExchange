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
	<p class='mySurveyTitle'><strong><%=session.getAttribute("title") %></strong></p>
	<p class='mySurveyDesc'><%=session.getAttribute("desc") %></p>
	<p class='myNumReplies'>Number of replies:<%=session.getAttribute("curNumReply") %>/<%=session.getAttribute("expNumReply") %></p>
	<div class='mySurveyContent'><%=session.getAttribute("qs") %></div>
	
	<script type='text/javascript' src='/SurveyExchange/mySurvey.js'></script>
</body>
</html>