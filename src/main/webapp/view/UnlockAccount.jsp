<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta charset="ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Unlock Password</title>

<script>
	$(document).ready(function() {

		$("#confirmPassword").blur(function() {
			var newpassword = $("#newPassword").val();
			var confirmpassword = $("#confirmPassword").val();

			if (newpassword != confirmpassword) {
				$("#CheckPasswordMatch").html("Passwords does not match!");
				$("#newPassword").focus();
				return false
			}
			$("#CheckPasswordMatch").fadeOut("slow");
			return true;
		})
	});
</script>

</head>
<body>
	<h1>Unlock Password</h1>

	

	<form:form action="/unlockaccount" modelAttribute="unlockdtls"
		method="POST">
		<table>
			<tr>
				<th>Email:</th>
				<td><form:input path="email" type="email" id="emailId"
						value="${email}" readonly="true" /></td>
			</tr>
			<tr>
				<th>TempPassword:</th>
				<td><form:input path="tempPassword" type="password"
						id="tempPassword" /></td>
			</tr>
			<tr>
				<th>NewPassword:</th>
				<td><form:input path="newPassword" type="password"
						id="newPassword" /></td>
			</tr>
			<tr>
				<th>ConfirmPassword:</th>
				<td><form:input path="confirmPassword" type="password"
						id="confirmPassword" />
					<div style="color: red;" id="CheckPasswordMatch"></div></td>
			</tr>

			<tr>
				<td><input type="submit" value="Reset">&nbsp; <input
					type="submit" value="save" id="btnSubmit"></td>

			</tr>
		</table>
	</form:form>
</body>
</html>