<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Time Clocker</title>

<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.common-material.min.css" />
<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.min.css" />
<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.mobile.min.css" />
<link href="resources/css/main.css" rel="stylesheet" type="text/css" />
<script src="https://kendo.cdn.telerik.com/2017.1.223/js/jquery.min.js"></script>
<script	src="https://kendo.cdn.telerik.com/2017.1.223/js/kendo.all.min.js"></script>
<script type="text/javascript" src="resources/js/app.js"></script>
</head>
<body>
	<a href="index">Go Back to Index Menu</a>

	<form:form method="POST" modelAttribute="timeclocker" action="submit">
	<p>Time Clocker for ${appuser.userId}</p>
	<p>From   ${timeclocker.firstDate}</p>
	<p>To     ${timeclocker.lastDate}</p>
	<div id="timeclockerApp">
		<div
			style="border-width: 1px; border-style: solid; border-color: rgb(125, 125, 125); width: 250px;">
			<br>
			<h4>Select Date</h4>
			<form:input id="datepicker" class="k-input" value="${getCurrentDate}" path="scanDateStr" style="width: 10" />
			<form:input id="timepicker" class="k-input" value="${getCurrentTime}" path="scanTimeStr" style="width: 10" />
			<br> <br>			
			<ul class="fieldlist">
	          <li>
	              <form:radiobutton name="timeclocker" id="clockIn1" class="k-radio" path="clockInOut" value="I" />
	              <label class="k-radio-label" for="clockIn1">CLOCK IN</label>
	          </li>
	          <li>
	              <form:radiobutton name="timeclocker" id="clockOut1" class="k-radio" path="clockInOut" value="O" checked="true"/>
	              <label class="k-radio-label" for="clockOut1">CLOCK OUT</label>
	          </li>
		        </ul>
		</div>
		<br>
		<br>
		<button id="primaryTextButton" class="k-button" type="submit" value="submit">Submit</button>
	</div>
	
	</form:form>
<!-- Closing Body used to be here, but moved to the out side of script-->	

</body>
<script>
	function onDateChange(e) {
		var dt = e.sender;
		var value = dt.value();
	
		if (value === null) {
			value = kendo.parseDate(dt.element.val(), dt.options.parseFormats);
		}
	
		if (value < dt.min()) {
			dt.value(dt.min());
		}else if (value > dt.max()) {
			dt.value(dt.max());
		}
	}

</script>

<script>
	$(document).ready(function() {
		var firstDate = ${mappedFirstDate}.split("|");
        var lastDate = ${mappedLastDate}.split("|");     
		
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
			},
		    min: new Date(firstDate[0],firstDate[1],firstDate[2]),
		    max: new Date(lastDate[0],lastDate[1],lastDate[2]),
		    change: onDateChange
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