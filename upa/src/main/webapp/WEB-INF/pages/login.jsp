<html xmlns:h="http://java.sun.com/jsf/html">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


 <c:set var="contextPath" value="${pageContext.request.contextPath}" />

<head>
 <!--
<link rel="stylesheet" type="text/css" href="resources/css/main.css"> -->
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet"> 


<script type="text/javascript" src="/resources/js/app.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
	
    <meta charset="utf-8"> 
</head>

<title>Log in with your account</title>

<body class="loginbody container-fluid">
	<div>
		<form:form id="loginForm" method="POST" modelAttribute="appuser" action="loginProcess" >
		<div class="logindiv">
			${message}
			
			<input type="text"     class="login" name="userId"      placeHolder="User Login ID"  value="KIWASAKI"/>
			<input type="password" class="login" name="userPassword" placeholder="Password" value="welcome1"/>
			<button class="login" type="submit">Log In</button>
			
		</div>
		</form:form>
		<footer class="footer">
			<p id="footerp">Copy right 2017</p>
		</footer>
	</div>
</body>


</html>