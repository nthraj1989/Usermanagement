<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta charset="ISO-8859-1">
<style type="text/css">
#div1 {
	margin: 50px;
	padding: 100px 50px;
}

#form {
	border: 10px dotted green;
	padding: 40px;
	width: 350px;
}
</style>
<title>ForgetPassword</title>
</head>
<body>
<div id="displaymessage">
		<h2>
			<font color='green'> <span>${successmsg}</span>
			</font>
			<font color='red'> <span>${errormsg}</span>
			</font>

		</h2>
	</div>


	<div align="right" id=div1>
		<form:form method="POST" action="/sendforgetpsswd"
			modelAttribute="forgetpassword" id="form">
			<table cellspacing="8px" cellpadding="5px">
				<thead>
					<tr>
						<th colspan="2">Forget password</th>
					</tr>
				</thead>

				<tbody>

					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email" type="email"
								placeholder="Enter email to get password" /></td>
					</tr>



					<tr>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td><input type="reset" value="reset">&nbsp; <input
							type="submit" value="Send" id="btnSubmit"></td>
					</tr>


				</tbody>
			</table>

		</form:form>
	</div>
</body>
</html>