<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:h="http://java.sun.com/jsf/html">

<head>
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<link rel="stylesheet" href="resources/styles/kendo.common.min.css" />
<link rel="stylesheet" href="resources/styles/kendo.default.min.css" />
<link rel="stylesheet" href="resources/styles/kendo.default.mobile.min.css" />
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/kendo.all.min.js"></script>
<script type="text/javascript" src="resources/js/app.js"></script>
</head>

<body>
	<h3>
		<a href="timeclock">Hand Scan Menu</a><br><br>
		<a href="timeclockrecord">Time Clock Sheet</a><br><br>
		 
		<a href="mytest">My Sandbox - all samples are here</a><br><br>
		<a href="showEmployeeForm">Add Employee</a><br><br>
		<a href="organizationView">Add Company</a><br><br>
		<button href="showEmployeeForm" value="Add Employee"></button><br><br>
		
		<a href="logout">Logout</a><br><br>
	</h3>

	<c:if test="${not empty lists}">
		<ul>
			<c:forEach var="listValue" items="${lists}">
				<li>${listValue}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>


</html>
