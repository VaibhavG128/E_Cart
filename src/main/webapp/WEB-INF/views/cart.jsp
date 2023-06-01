<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./includes/head.jsp"%>
<title>E-Cart</title>
<style type="text/css">

.table tbody td{
vertical-align: middle;
}
.btn-incre, .btn-decre{
box-shadow: none;
font-size: 25px;
}
</style>
</head>
<body>
	<%@include file="./includes/navbar.jsp"%>

	<div class="container my-3">
		<div class="d-flex py-3"><h3>Total Price:  ${(total>0)?dcf.format(total):0} </h3> <a class="mx-3 btn btn-primary" href="cartCheckOut">Check Out</a></div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<d:forEach items="${cart_list}" var="v">
				<tr>
					<td>${v.getName()}</td>
					<td>${v.getCategory()}</td>
					<td>${v.getPrice()*v.getQuantity()}</td>
					<td>
						<form action="orderNow" method="get" class="form-inline">
						<input type="hidden" name="id" value="${v.getId()}" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn bnt-sm btn-incre" href="quantity?action=inc&id=${v.getId()}"><i class="fas fa-plus-square"></i></a> 
								<input type="text" name="quantity" class="form-control"  value="${v.getQuantity()}" readonly> 
								<a class="btn btn-sm btn-decre" href="quantity?action=dec&id=${v.getId()}"><i class="fas fa-minus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
					</td>
					<td><a href="removeFromCart?id=${v.getId()}" class="btn btn-sm btn-danger">Remove</a></td>
				</tr>
			</d:forEach>
			</tbody>
		</table>
	</div>

	<%@include file="./includes/footer.jsp"%>
</body>
</html>