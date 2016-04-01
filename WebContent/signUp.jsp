<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="/SurveyExchange/stylesheet.css">
</head>
<body>
	<form action="SignUpServlet" method="post">
		<fieldset style="width: 300px">
			<legend> Sign up </legend>
			<table>
				<tr>  
                    <td>Email:</td>  
                    <td><input type="email" name="email" required="required" /></td>  
                </tr>  
                <tr>  
                    <td>Username:</td>  
                    <td><input type="text" name="username" required="required" /></td>  
                </tr>
                <tr>  
                    <td>Password:</td>  
                    <td><input type="password" name="pwd" required="required" /></td>  
                </tr>  
                <tr>  
                    <td>Retype Password:</td>  
                    <td><input type="password" name="pwd2" required="required" /></td>  
                </tr>
                <tr>
                	<td><input type="submit" value="Sign up" /><a href="welcome.jsp"><input type="button" value="Login Page" /></a></td>
                </tr>
			</table>
		</fieldset>
	</form>
</body>
</html>