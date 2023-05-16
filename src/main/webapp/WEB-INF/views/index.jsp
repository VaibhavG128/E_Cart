<%@page import="java.util.*"%>
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
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<d:forEach items="${productList}" var="v">
				<div class="col-md-3 my-3">
					<div class="card w-100">
						<img class="card-img-top" src="product-image/${v.getImage()}"
							alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title">${v.getName()}</h5>
							<h6 class="price">Price: ${v.getPrice()}</h6>
							<h6 class="category">Category: ${v.getCategory()}</h6>
							<div class="mt-3 d-flex justify-content-between">
								<a class="btn btn-dark" href="add-to-cart?id=${v.getId()}">Add
									to Cart</a> <a class="btn btn-primary"
									href="order-now?quantity=1&id=${v.getId()}">Buy Now</a>
							</div>
						</div>
					</div>
				</div>
			</d:forEach>
		</div>
	</div>

	<%@include file="./includes/footer.jsp"%>
</body>
</html>