<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="./includes/head.jsp"%>
<title>E-Cart</title>
</head>
<body>
	<%@include file="./includes/navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
			<input type="hidden" id="userStatus" value="${newUser}">
				<form action="validate" method="post">
					<div class="form-group">
						<label>Email address</label> <input type="email"
							name="login-email" class="form-control" placeholder="Enter email">
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password"
							name="login-password" class="form-control" placeholder="Password">
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
				</form>
			</div>
			<a href="/registrationPage" class="text-center">Create Account</a>
		</div>
	</div>

	<%@include file="./includes/footer.jsp"%>
	<!-- <script type="text/javascript">
	$( document ).ready(function() {
	   var status = $('#userStatus').val();
	   console.log(status);
	   if(status!="" && status){
	   	alert("You are successfully registered")
	   }
	   	else if (status!="" && status){
	   	alert("You are Not Registered Try again!!")
	   	}
	});
	</script> -->
</body>
</html>