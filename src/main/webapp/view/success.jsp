<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<body>
	<div id="displaymessage">
		<h2>
			<font color='green'> <span>${msg}</span>
			</font>

		</h2>
	</div>

	<h1>
		<font style="color: navy; border-bottom: solid;"><a
			href="login">Click here to login</a></font>
	</h1>
</body>
</html>