<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<title>Registration</title>

<script>
	$(document).ready(
			function(event) {
				$("#datepicker").datepicker({
					maxDate : -1
				});
				
				
				 		 
			  $("#emailId").blur(function(){
					  var emailId = $("#emailId").val();
					 
					  $.ajax({
						  type : "GET",
							url : "/checkduplicateemail?emailId=" + emailId,
							async : false,
							success : function(response) {

							  if(response !== true){
								  $("#errmsg").append("EmailId already exist");
								 $("#emailId").focus();
							  }
							  else{
								  $( "#emailId" ).blur(function() {
									  $( "#errmsg" ).fadeOut( "slow",)
									});
								  return false;
							  }
							 
						  }
					  })
					});

				$("#countryId").change(
						function() {
							$('#stateId').find('option').remove();
							$('#stateId').append($('<option></option>').val('').html(
											'--select--'));

							$('#cityId').find('option').remove();
							$('#cityId').append(
									$('<option></option>').val('').html(
											'--select--'));

							var countryid = $('#countryId').val();
							//alert(countryid);
							$.ajax({
								type : "GET",
								url : "/getstates?cid=" + countryid,
								async : false,
								success : function(response) {
									//alert("seccess");
									$.each(response, function(key, value) {
										$('#stateId').append(
												$('<option></option>').val(key)
														.html(value));
									})
								}
							})
						})

				$("#stateId").change(
						function(event) {
							$('#cityId').find('option').remove();
							$('#cityId').append(
									$('<option></option>').val('').html(
											'--select--'));
							var stateId = $('#stateId').val();
							//alert("stateId")
							$.ajax({
								type : "GET",
								url : "/getcities?sid=" + stateId,
								async : false,
								success : function(response) {
									$.each(response, function(key, value) {
										$('#cityId').append(
												$('<option></option>').val(key)
														.html(value));
									})
								}
							})
						})

			});
</script>

</head>
<body>

	<div id="displaymessage">
		<h2>
			<font color='green'> <span>${msg}</span>
			</font>
		</h2>
	</div>
<script>
      $(document).ready(function(){
	
		setTimeout(function() {
			$('#displaymessage').fadeOut("slow");
		}, 2000);
     });
</script>


	<h1>Registration Page</h1>

	<form:form action="/registration" method="POST" modelAttribute="user"
		id="form">
		<table>
			<tr>
				<th>First Name:</th>
				<td><form:input path="firstName" /> <span id="fname"></span></td>
			</tr>
			<tr>
				<th>Last Name:</th>
				<td><form:input path="lastName" /> <span id="lname"></span></td>
			</tr>
			<tr>
				<th>Email:</th>
				<td><form:input path="email" type="email" id="emailId" /> <font
					color="red"> <span id="email"></span> <span id="errmsg"></span></font>
				</td>
			</tr>
			<tr>
				<th>Mobile:</th>
				<td><form:input path="PhoneNo" /> <span id="phn"></span></td>
			</tr>
			<tr>
				<th>Date-Of-Birth:</th>
				<td><form:input path="DOB" id="datepicker" /> <span id="dob"></span>
				</td>
			</tr>
			<tr>
				<th>Gender:</th>
				<td>Male:<form:radiobutton path="sex" value="M" id="gender" />
					Female:<form:radiobutton path="sex" value="F" id="gender" /> <span
					id="gender"></span>
				</td>
			</tr>
			<tr>
				<th>Country:</th>
				<td><form:select path="countryId">
						<form:option value="">-Select-</form:option>
						<form:options items="${allcountries}" />
					</form:select> <span id="countryId"></span></td>
			</tr>
			<tr>
				<th>State:</th>
				<td><form:select path="stateId">
						<form:option value="">-Select-</form:option>
					</form:select> <span id="stateId"></span></td>
			</tr>
			<tr>
				<th>City:</th>
				<td><form:select path="cityId">
						<form:option value="">-Select-</form:option>
					</form:select> <span id="cityId"></span></td>

			</tr>

			<tr>
				<th></th>
				<td><input type="submit" value="Reset">&nbsp; <input
					type="submit" value="save" id="btnSubmit"></td>
			</tr>
		</table>
	</form:form>



</body>
</html>