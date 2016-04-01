<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js'></script>
<link rel="stylesheet" type="text/css" href="/SurveyExchange/stylesheet.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Surveys</title>
</head>
<body>
	<div id='title'>My Surveys</div>
	<div id='surveysContainer'><%=session.getAttribute("questions") %></div>
	
<script type='text/javascript' src='/SurveyExchange/mySurveys.js'></script>
</body>
</html>