<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Welcome</title>
<link rel="stylesheet" type="text/css" href="/SurveyExchange/stylesheet.css">
</head>  
<body>
    <form action="LoginServlet" method="post">  
        <fieldset style="width: 300px">  
            <legend> Login to App </legend>  
            <table>  
                <tr>  
                    <td>Email:</td>  
                    <td><input type="email" name="email" required="required" /></td>  
                </tr>  
                <tr>  
                    <td>Password:</td>  
                    <td><input type="password" name="pwd" required="required" /></td>  
                </tr>  
                <tr>  
                    <td><input type="submit" value="Login" /><a href="signUp.jsp"><input type="button" value="Sign Up Page" /></a></td>  
                </tr>
            </table>  
        </fieldset>  
    </form>  
</body>  
</html>