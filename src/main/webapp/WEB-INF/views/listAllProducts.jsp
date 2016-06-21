<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Products</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"
	href='<c:url value="resources/css/bootstrap.min.css"/>'>
<script src="<c:url value="resources/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="resources/js/angular.min.js"/>"></script>
<script src="<c:url value="resources/js/bootstrap.min.js"/>"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
</head>
<body>
	<%
		String str = request.getParameter("id");
	%>
	<div class="container">
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

		<div class="container">
			<div class="container" style="margin-top: 4%" ng-app="tabApp"
				ng-controller="tabCtrl">
				<input class="form-control" placeholder="Search" type="text"
					ng-model="str">

				<h3><%=str%>
				</h3>
				<table class="table table-bordered">
					<tbody>
						<tr ng-repeat="x in data">
							<td><img
							<%=str%>
								src="<c:url value="/resources/images/tablets/3.jpg"/>"
								alt="Tablet" style="width: 300px; height: 150px"></td>
							<td>{{x.id}}</br> {{x.PrdName}}</br> {{x.Price}}</br> {{x.Discount}}</br>
								{{x.Warranty}}</br> {{x.Category}}
							</td>

						</tr>
					</tbody>
				</table>

			</div>
			<script>
				var app = angular.module('tabApp', []);
				app.controller('tabCtrl', function($scope, $http) {
					$http.get("<%=str%>
				").then(function(response) {
						$scope.data = response.data
					})
				});
			</script>
		</div>
</body>
</html>