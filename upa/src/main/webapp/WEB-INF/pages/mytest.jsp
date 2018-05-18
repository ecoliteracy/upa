<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC - HelloWorld Index Page</title>
<link href="resources/css/main.css" rel="stylesheet" type="text/css" />
<link href='resources/css/calendar.css' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<link rel="stylesheet" href="resources/styles/kendo.common.min.css" />
<link rel="stylesheet" href="resources/styles/kendo.default.min.css" />
<link rel="stylesheet" 	href="resources/styles/kendo.default.mobile.min.css" />
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/kendo.all.min.js"></script>
<script type="text/javascript" src="resources/js/app.js"></script>

</head>
<h2>${appuser.loginId}</h2>
<h2>You are now at mytest.jsp</h2>
<p> Cleck to go to Calender Project <a href="calendar">Calendar Test</a><br><br> </p>





<h3>
	<a href="third">Click Here</a>
</h3>

<button name="button" value="OK" type="button" onclick="hello()">Click Here to test Java Script</button>


<h1>The time on the server is ${myBean.toString()}</h1>
<c:if test="${not empty lists}">

	<ul>
		<c:forEach var="listValue" items="${lists}">
			<li>${listValue}</li>
		</c:forEach>
	</ul>
</c:if>


		<div align='left'>
			Select Date: <input type='text' id='sel' onclick='dispCal()' size=10
				readonly='readonly' /> <img src='resources/img/calendar.png'
				onclick='dispCal()' style='cursor: pointer; vertical-align: middle;' />
			<table class='calendar' id='calendar' border=0 cellpadding=0
				cellspacing=0>
				<tr class='monthdisp'>
					<td class='navigate' align='left'><img
						src='resources/img/previous.png' onclick='return prev()' /></td>
					<td align='center' id='month'></td>
					<td class='navigate' align='right'><img
						src='resources/img/next.png' onclick='return next()' /></td>
				</tr>
				<tr>
					<td colspan=3>
						<table id='dispDays' border=0 cellpadding=4 cellspacing=4>
						</table>
					</td>
				</tr>
			</table>
		</div>

</body>
</html>