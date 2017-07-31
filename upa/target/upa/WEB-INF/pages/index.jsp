<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:h="http://java.sun.com/jsf/html">

<head>
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<link rel="stylesheet" href="resources/styles/kendo.common.min.css" />
<link rel="stylesheet" href="resources/styles/kendo.default.min.css" />
<link rel="stylesheet"
	href="resources/styles/kendo.default.mobile.min.css" />
<script type="text/javascript" src="resources/js/app.js"></script>
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/kendo.all.min.js"></script>
</head>

<body>

	<h3>Select Your Favorite Cuisine :</h3>
	<form>
		<input type="radio" name="clocking" value="clockIn" checked>
		Clock In<br> <input type="radio" name="clocking" value="clockOut">
		Clock Out<br> <input type="submit" value="Submit">
	</form>


	<!-- 
	<h2>Spring MVC and List Example</h2>
	<h2>MyBean: ${myBean.name}</h2>
	<h2>MyBean: ${hi}</h2>
	<h:input type="button" id="sample_button" value="getCurrentDateTime"
		onclick="getCurrentDateString()" />
			 -->

	<h2>Current Date Time: ${currentDateTime}</h2>
	<c:set var="test" scope="request" value="${requestScope.userDetails }"></c:set>

	<button name="button" value="OK" type="button" onclick="hello()">Click
		Here 2</button>

	<h3>
		<a href="handscan">Hand Scan Menu</a><br> <br> <a
			href="calendar">Calendar Test</a><br> <br> <a href="mytest">My
			Test</a><br> <br>
		<a href="showEmployeeForm">Add Employee</a><br><br>
		<button href="showEmployeeForm" value="Add Employee"></button>
		
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
