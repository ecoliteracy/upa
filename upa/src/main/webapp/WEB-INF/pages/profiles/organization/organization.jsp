<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Organization Profile Page</title>

<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.common-material.min.css" />
<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.min.css" />
<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.mobile.min.css" />
<link href="resources/css/main.css" rel="stylesheet" type="text/css" />
<script src="https://kendo.cdn.telerik.com/2017.1.223/js/jquery.min.js"></script>
<script	src="https://kendo.cdn.telerik.com/2017.1.223/js/kendo.all.min.js"></script>
<script type="text/javascript" src="resources/js/app.js"></script>

</head>
<body>
 	<form:form method="post" modelAttribute="org" action="submitNewOrg">
		<div id="submitNewOrg"> 
			<table>
				<tr>
					<h4>Organization Profile</h4>
				</tr>
				<tr>${result}</tr>
				<tr>
					<td>
						Company Name :
					</td>
					<td>
						<form:input id="orgName" path="orgName" 
						size="50" maxlength="50"
						class="uppercase"
						onkeyup="this.value=this.value.toUpperCase()"/>
					</td>
		     	</tr>
		     	<tr>
		     		<td>
						<form:select path = "orgType">
							<form:options items="${orgTypeList}" />
						</form:select>
		     		</td>
		     	</tr>
		     	<tr>
			     	<td>
						<input id="primaryTextButton" type="submit" 
						name="submitNewOrg" value="SUBMIT">
					</td>
					<td>
						<button id="primaryTextButton" type="submit" 
						name="submitNewOrg" value="SUBMIT">SUBMIT</button>
					</td>
				</tr>
			</table>
		</div>
	</form:form>
</body>
</html>