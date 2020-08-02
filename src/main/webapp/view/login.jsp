<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<div align="right" id=div1>
		<form:form method="POST" action="/loginacc" modelAttribute="userlogindtls" id="form">
			<table cellspacing="8px" cellpadding="5px">
				<thead>
					<tr>
						<th colspan="2">Login Page</th>
					</tr>
				</thead>

				<tbody>

					<tr>
						<td><label>UserName</label></td>
						<td><form:input path="email" type="email"
								placeholder="Emial required" /></td>
					</tr>



					<tr>
						<td><label>password</label></td>
						<td><form:input path="password" type="password"
								placeholder="password required" /></td>
					</tr>

					<tr>
						<td></td>
						<td></td>
					</tr>

					<tr>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td><input type="reset" value="reset">&nbsp; <input
							type="submit" value="save" id="btnSubmit"></td>
					</tr>

					<tr>
						<td><a href="register">sign up</a></td>
						<td>&nbsp;</td>

						<td><a href="forgetpwd">Forget password</a></td>
					</tr>

				</tbody>
			</table>

		</form:form>
	</div>

</body>
</html>