<!DOCTYPE html>
<html lang="en">
<head>
<title>Registration Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<div class="container">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Electronic E-Commerce</a>
				</div>
				<button class="navbar-toggle" data-toggle="collapse"
					data-target=".navHeaderCollapse">

					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

				<div class="collapse navbar-collapse navHeaderCollapse">
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="<c:url value='/index'/>">Home</a></li>
						<li><a href="<c:url value='/signin'/>"> <span
								class="glyphicon glyphicon-user"></span> Login
						</a> <!--  <a id="myBtn" style="cursor: pointer">Login</a></li>-->
					</ul>
				</div>
			</div>
		</nav>
		<h2>Registration Form</h2>
		<form:form method="POST" commandName="cmdRegister" action="saveRegister">
			<div class="form-group">
				<div class="col-sm-8">
					<form:label path="UserName">User Name:</form:label>
					<br />
					<form:input path="UserName" size="50" ></form:input>
					<br />
					<form:errors path="UserName" cssClass="error" />
				</div>
				<div class="col-sm-8">
					<form:label path="Password">Password:</form:label>
					<br />
					<form:input path="Password" size="50" />
					<br />
					<form:errors path="Password" cssClass="error"/>
				</div>

				<div class="col-sm-8">
					<form:label path="FirstName">First Name:</form:label>
					<br />
					<form:input path="FirstName" size="50" />
					<br />
					<form:errors path="FirstName" cssClass="error"/>
				</div>

				<div class="col-sm-8">
					<form:label path="LastName">Last Name:</form:label>
					<br />
					<form:input path="LastName" size="50" />
					<br />
					<form:errors path="LastName" cssClass="error"/>
				</div>
				
				<div class="col-sm-8">
					<form:label path="Email">Email:</form:label>
					<br />
					<form:input path="Email" size="50" ></form:input>
					<br />
					<form:errors path="Email" cssClass="error" />
					<br>
				</div>
				
				<div class="col-sm-8">
					<button type="submit" class="btn btn-success">Submit</button>
					<br />
				</div>
			</div>
		</form:form>

	</div>
<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>
</html>

