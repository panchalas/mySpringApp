<html>
<head>
<title>Welcome to Electronics E-commerce</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Electronic E-Commerce
					&nbsp;&nbsp;</a>
			</div>
			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navHeaderCollapse">

				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<div class="collapse navbar-collapse navHeaderCollapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value='/index'/>">Home</a></li>
					<li><a href="<c:url value='/addProduct'/>">Add Product</a></li>
					<li><a href="<c:url value='/contactUs'/>">Contact Us</a></li>
					<li><a href="<c:url value='/aboutUs'/>"> About Us</a></li>
					<li><a href="<c:url value='/adminLogin'/>"> <span
							class="glyphicon glyphicon-user"></span> Login
					</a></li>
					<li><a href="<c:url value='/register'/>"> <span
							class="glyphicon glyphicon-user"></span> Sign Up
					</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<img src="resources/images/image1.jpg" alt="Image1">
			</div>

			<div class="item">
				<img src="resources/images/image2.jpg" alt="Image2">
			</div>

			<div class="item">
				<img src="resources/images/image3.jpg" alt="Flower">
			</div>
		</div>

		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<!--  <a href="search?data=camera"> -->
				<a href="<c:url value='/listProducts?cat=ehdd'/>"> <img
					src="<c:url value="/resources/images/ehdd1.jpg"/>" alt="External Hard Drive"
					style="width: 300px; height: 150px">
				</a>
			</div>
			<div class="col-sm-4">
				<a href="<c:url value='/listProducts?cat=camera'/>"> <img
					src="<c:url value="/resources/images/image1.jpg"/>" alt="Camera"
					style="width: 300px; height: 150px">
				</a>
			</div>
			<div class="col-sm-4">
				<a href="<c:url value='/listProducts?cat=tablet'/>"> <img
					src="<c:url value="/resources/images/tablet1.jpg"/>" alt="Tablet"
					style="width: 300px; height: 150px">
				</a>
			</div>
		</div>
	</div>

	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
</body>
</html>
