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
		<input type="hidden" id = 'clist1' value='${cart_list.size()}'>
		<input type="hidden" id = 'clist2' value='${cart_list}'>
		
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
									href="orderNow?quantity=1&id=${v.getId()}">Buy Now</a>
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
	try{
	console.log('==>',$('#clist').val(),$('#clist1').val(),$('#clist2').val())	
	var cardList1 = $('#clist').val();
	var cardList;
	if(cardList1!=undefined && cardList1.length>0){
	cardList = JSON.parse(cardList1)
	}
	console.log('==>',cardList,'<==',id);
	console.log(cardList, typeof cardList)
	console.log(cardList)
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
	catch(err)
	{
		console.log(err)
		return false;
	}

} 

function check1Cart(id,cardList)
{
	return true;
}
</script>
</html>