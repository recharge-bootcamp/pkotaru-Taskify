<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
</head>
<body>
	<form action="${pageContext.request.contextPath}/LoginController" method="POST">

		UserName <input type="text" class="form-control" name="un" /><br>

		Password <input type="password" class="form-control" name="pw" /> 
		
		<input
			type="submit" class="form-control" value="Login">
			
			
			<input
			type="submit" class="form-control" value="Sign Up">
	</form>
</body>
</html>