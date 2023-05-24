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
		<div class="card-header my-3">All Products</div>
		<div class="row">
		<input type="hidden" id = 'clist' value='${cartList}'>
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
								 <a class="btn btn-dark"  onclick="return checkCart(${v.getId()});" href="addToCart?id=${v.getId()}"  >Add
									to Cart</a>
									 <a class="btn btn-primary"
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
<script type="text/javascript">
function checkCart(id)
{
	
	var cardList1 = $('#clist').val();
	var cardList = JSON.parse(cardList1)
	console.log('==>',cardList,'<==',id);
	console.log(cardList, typeof cardList)
	console.log(cardList)
		 console.log( Object.keys(cardList) )
		 console.log( cardList[id])
	 if(cardList != undefined && Object.keys(cardList) && cardList[id]!==undefined)
		{
		 console.log(cardList)
		 console.log( Object.keys(cardList) )
		 console.log( cardList[id])
		alert('Item Already in Cart.');
		return false;
		}
	else
		{
		alert('Item Already in Cart Not.');
		return true;
		} 

} 

function check1Cart(id,cardList)
{
	return true;
}
</script>
</html>