<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
			
		<a class="navbar-brand" href="/">
		<img src="./product-image/ECartLogo.jpeg" class="img-fluid" style="max-width: 20%; border-radius: 100%">
		E-Cart</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="/">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="/cart">Cart<span id = "cardSize" class="badge badge-danger">${cart_list.size()}</span> </a></li>
				<d:choose>
				<d:when test="${auth!=null}" >
				<d:choose>
				<d:when test="${auth.userType == 'seller'}">
				 <li class="nav-item"><a class="nav-link" href="/registrationProduct">RegisterProduct</a></li>
				</d:when>
				<d:otherwise>
				 </d:otherwise>
				 </d:choose>
				 <li class="nav-item"><a class="nav-link" href="/order">Orders</a></li>
				 <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
				</d:when>
				<d:otherwise>
				<li class="nav-item"><a class="nav-link" href="/login"> Login</a></li> 
				</d:otherwise>
				</d:choose>
			</ul>
		</div>
	</div>
</nav>