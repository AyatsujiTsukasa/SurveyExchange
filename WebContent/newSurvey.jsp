<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js'></script>
<link rel='stylesheet' type='text/css' href='/SurveyExchange/stylesheet.css'>
<% 
String name = session.getAttribute("name").toString();
String pwd = session.getAttribute("pwd").toString();
String credit = session.getAttribute("credit").toString();
String email = session.getAttribute("email").toString();
String id = session.getAttribute("id").toString();
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<title><%=name %> - New Survey</title>
</head>
<body>
	<h2>New Survey</h2>
	<hr>
	<form>
		<fieldset>
			<legend> Design Your New Survey Below! </legend>
			<table>
				<tr>  
                    <td>Title:</td>
                    <td><input name='title' type='text' id='SurveyTitle' required='required' /></td>
                </tr>
                <tr>  
                    <td>Expected Number of Replies:</td>
                    <td><input name='expectedNumReply' type='number' id='expNumReply' required='required' onchange='expNumReplyChange(this)' /></td>
                </tr>
                <tr>
                	<td>Remaining Credits: </td>
                	<td><span id='credit'><%=credit %></span></td>
                </tr>
                <tr>  
                    <td>Description:</td>  
                    <td><textarea name="desc" cols="73" rows="5" id='desc'></textarea></td>
                </tr>
                <tr>
                	<td>Author:</td>
                	<td><span id='author'><%=name %></span></td>
                	<td><input type=hidden id='authorID' value=<%=id %> /></td>
                </tr>
                <tr>
                	<td>Email:</td>
                	<td><span id='email'><%=email %></span></td>
                </tr>
			</table>
			<table>
				<ol id='questionList'></ol>
				<hr><tr><td><div class='addNew addNewQ' onclick='addNewQuestion()'>Add a new question</div></td></tr>
                <tr><td><input type='button' value='< Cancel' id='toHome' onclick="onclick=location.href = 'userHome.jsp'" /><input type='button' value='Next >' onclick="switchPage()" /></td></tr>
			</table>
		</fieldset>
	</form>


	<script type='text/javascript' src='/SurveyExchange/admin.js'></script>
</body>
</html>