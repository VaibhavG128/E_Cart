<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./includes/navbar.jsp"%>
<title>Registration Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
	background-color: #f5f5f5;
	font-family: Arial, sans-serif;
}

.container1 {
	max-width: 80%;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	margin-top: 2%;
}

.container1 h1 {
	text-align: center;
	font-size: 24px;
	margin-bottom: 20px;
}

.form-group {
	margin-bottom: 20px;
	text-align: center;
}

.form-group label {
	font-weight: bold;
}

.form-control {
	width: 50%;
	margin-left: 25%;
	text-align: center; padding : 10px;
	font-size: 16px;
	border-radius: 4px;
	border: 1px solid #ccc;
	padding: 10px
}

.btn {
	width: 100%;
	padding: 10px;
	font-size: 16px;
	border-radius: 4px;
	border: none;
	background-color: #ff9900;
	color: #fff;
	cursor: pointer;
}

.btn:hover {
	background-color: #ff6600;
}
</style>
</head>

<body>
	<div class="container1">
		<h1>Registration Page</h1>

		<form method="post" action="registerAction" onsubmit="return validate()">
			<div class="form-group">
				<label for="name">Name:</label> <input type="text" name="name"
					id="name" class="form-control" required>
			</div>

			<div class="form-group">
				<label for="email">Email:</label> <input type="email" name="email"
					id="email" class="form-control" required>
			</div>

			<div class="form-group">
				<label for="userType">User Type:</label> <select name="userType"
					id="userType" class="form-control" required>
					<option value="seller">Seller</option>
					<option value="buyer">Buyer</option>
				</select>
			</div>

			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					name="password" id="password" class="form-control" required>
			</div>

			<div class="form-group">
				<label for="confirmPassword">Repeat Password:</label> <input
					type="password" name="confirmPassword" id="confirmPassword"
					class="form-control" required>
			</div>

			<button type="submit" class="btn">Register</button>
		</form>
	</div>

	<script type="text/javascript">
	
	function validate()
	{
		if($('#confirmPassword').val() == $('#password').val())
			{
			return true;
			}
		else
			{
			alert("Password is not same")
			return false;
			}
	}
	
	</script>
</body>
</html>
