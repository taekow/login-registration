<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<title>Registration Page</title>
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<script type="text/javascript" src="js/app.js"></script>
	<!-- for Bootstrap CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row pt-5">
			<div class="col">
				<h1>Register User</h1>
				<form:form action="/registration" method="post" modelAttribute="newUser">
					<div class="form-group pt-3">
						<form:label path="userName">User Name:</form:label>
				        <form:errors path="userName" class="text-danger"/>
				        <form:input type="text" path="userName" class="form-control" style="width:500px;"/>
				    </div>
				    <div class="form-group pt-3">
				        <form:label path="email">Email:</form:label>
						<form:errors path="email" class="text-danger"/>
						<form:input type="email" path="email" class="form-control" style="width:500px;"/>
				    </div>
				    <div class="form-group pt-3">
				        <form:label path="password">Password:</form:label>
				        <form:errors path="password" class="text-danger"/>     
				        <form:password path="password" class="form-control" style="width:500px;"/>
				    </div>
				    <div class="form-group pt-3">
				    	<form:label path="confirm">Confirmation Password:</form:label>
				        <form:errors path="confirm" class="text-danger"/>
				        <form:password path="confirm" class="form-control" style="width:500px;"/>
				    </div>
					<div>
						<input type="submit" class="btn btn-info mt-3" value="Register"/>
					</div>
				</form:form>
			</div>
			<div class="col">
				<h1>Login User</h1>
				<form:form action="/login" modelAttribute="newLogin">
					<div>
						 <form:label path="email">Email:</form:label><br />
				        <form:errors path="email" class="text-danger"/>
				        <form:input type="email" path="email" class="form-control" style="width:500px;"/>
				    </div>
				    <div>
				        <form:label path="password">Password: </form:label><br />
						<form:errors path="password" class="text-danger"/>
						<form:password path="password" class="form-control" style="width:500px;"/>
				    </div>
					<div>
						<input type="submit" class="btn btn-info mt-3" value="login"/>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>