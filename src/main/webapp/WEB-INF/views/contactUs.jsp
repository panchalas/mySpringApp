<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact Us</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
					<li><a href="<c:url value='/contactUs'/>">Contact Us</a></li>
					<li><a href="<c:url value='/aboutUs'/>"> About Us</a></li>
					<li><a href="<c:url value='/signin'/>"> <span
							class="glyphicon glyphicon-user"></span>Login
					</a></li>
					<li><a href="<c:url value='/addRegister'/>"> <span
							class="glyphicon glyphicon-user"></span> Sign Up
					</a></li>
				</ul>
			</div>
		</div>
	</nav>

<center>
<img src="resources/images/contactus.jpg" alt="Contact Us">
<br>
<h3>ABC Inc</h3>
<h4>aspanchal2000@gmail.com</h4>
<h4>9876543210</h4>

</center>
</body>
</html>