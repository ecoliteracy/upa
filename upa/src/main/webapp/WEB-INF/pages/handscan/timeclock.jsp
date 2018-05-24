<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC - HelloWorld Index Page</title>

<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.common-material.min.css" />
<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.min.css" />
<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.mobile.min.css" />
<link href="resources/css/main.css" rel="stylesheet" type="text/css" />
<script src="https://kendo.cdn.telerik.com/2017.1.223/js/jquery.min.js"></script>
<script	src="https://kendo.cdn.telerik.com/2017.1.223/js/kendo.all.min.js"></script>
<script type="text/javascript" src="resources/js/app.js"></script>
</head>
<body>
	<form:form method="POST" modelAttribute="timeclocker" action="submit">
	<p>Time Clocker for ${appuser.userId}</p>
	<p>From ${timeclocker.firstDate}</p>
	<p>To   ${timeclocker.lastDate}</p>
	<div id="timeclockerApp">
		<div
			style="border-width: 1px; border-style: solid; border-color: rgb(125, 125, 125); width: 250px;">
			<br>
			<h4>Select Date</h4>
			<form:input id="datepicker" value="${getCurrentDate}" path="scanDateStr" style="width: 10" />
			<form:input id="timepicker" value="${getCurrentTime}" path="scanTimeStr" style="width: 10" />
			<br> <br>
			<form:radiobutton path="clockInOut" name="timeclocker" value="I" checked="checked" />CLOCK IN
			<form:radiobutton path="clockInOut" name="timeclocker" value="O" />CLOCK OUT
		</div>
		<br>
		<br>
		<input type="submit" value="Submit">
		<button id="primaryTextButton" type="submit" value="submit">Submit</button>
	</div>
	</form:form>
</body>
<script>
	$(document).ready(function() {
		// create below from input HTML element
		$("#billingperiodselect").kendoDropDownList();
		/*How to get the value of this DropDownList*/
		//var billingperiodselect = $("#billingperiodselect").data("kendoDropDownList"); 
		
		$("#datetimepicker").kendoDateTimePicker({});

		$("#datepicker").kendoDatePicker({
			animation : {
				close : {
					effects : "fadeOut zoom:out",
					duration : 200
				},
				open : {
					effects : "fadeIn zoom:in",
					duration : 200
				}
			}
		});
		
		$("#firstdatepicker").kendoDatePicker({
			animation : {
				close : {
					effects : "fadeOut zoom:out",
					duration : 200
				},
				open : {
					effects : "fadeIn zoom:in",
					duration : 200
				}
			}
		});
		
		$("#lastdatepicker").kendoDatePicker({
			animation : {
				close : {
					effects : "fadeOut zoom:out",
					duration : 200
				},
				open : {
					effects : "fadeIn zoom:in",
					duration : 200
				}
			}
		});
		
		$("#timepicker").kendoTimePicker({
			interval : 5,
			animation : {
				close : {
					effects : "fadeOut zoom:out",
					duration : 200
				},
				open : {
					effects : "fadeIn zoom:in",
					duration : 200
				}
			}
		});

		$("#monthpicker").kendoDatePicker({
			// defines the start view
			start : "year",

			// defines when the calendar should return date
			depth : "year",

			// display month and year in the input
			format : "MMMM yyyy"
		});
	});
</script>
</html>