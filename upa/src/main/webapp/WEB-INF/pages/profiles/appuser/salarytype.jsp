<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>App User's Profile</title>

<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.common-material.min.css" />
<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.min.css" />
<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.mobile.min.css" />
<link href="resources/css/main.css" rel="stylesheet" type="text/css" />
<script src="https://kendo.cdn.telerik.com/2017.1.223/js/jquery.min.js"></script>
<script	src="https://kendo.cdn.telerik.com/2017.1.223/js/kendo.all.min.js"></script>
<script type="text/javascript" src="resources/js/app.js"></script>
</head>
<body>
	<p>App User Profile</p>

	<p>This is for ${appuser.userId} </p>
	<form:form method="POST" modelAttribute="userSalaryType" action="payPeriodSubmit">
	
		Pay Period: 
		<form:select id="billingperiodselect" name="item" path="payPeriodType">
			<option value="0"></option>
			<option value="1">Monthly</option>
			<option value="2">Twice a month</option>
			<option value="3">Bi-Weekly</option>
			<option value="4">Weekly</option>
			<option value="5">Daily</option>
		</form:select><br/> 
	
	<div id="appUserProfile">
		<button id="primaryTextButton" type="submit" value="payPeriodSubmit">Submit</button>
	</div>
	</form:form>
	
</body>
<script>
	$(document).ready(function() {});
</script>
</html>