<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!DOCTYPE html>
<html>
<head>
<%@include file="./includes/head.jsp"%>
<title>E-Commerce Cart</title>
</head>
<body>
	<%@include file="./includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<d:forEach items="${ordersList}" var="o">			
					<tr>
						<td>${o.getDate()}</td>
						<td>${o.getName()}</td>
						<td>${o.getCategory()}</td>
						<td>${o.getQunatity()}</td>
						<td>${o.getPrice()*o.getQunatity()}</td>
						<td><a class="btn btn-sm btn-danger" href="cancelOrder?id=${o.getOrderId()}">Cancel Order</a></td>
					</tr>
			</d:forEach>
			
			</tbody>
		</table>
	</div>
	<%@include file="./includes/footer.jsp"%>
</body>
</html>