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
	<p>This is for ${appuser.userId}</p>
	<form:form method="POST" modelAttribute="userSalaryType"
		action="submitUserPayPeriod">
		<div id="appUserProfile">
			<label for="firstDate" class="required">First Date</label>
			<form:input type="text" id="firstdatepicker" path="firstDate" name="FirstDate" 
			class="k-textbox" placeholder="First Date" style="width: 220px;" required="true"></form:input>
			<br/> Payroll Period:
			<form:select id="billingperiodselect" name="item"
				onchange="addDateToLastDate()" path="payPeriodType" required="true">
				<option value="0"></option>
				<option value="1">Monthly</option>
				<option value="2">Twice a month</option>
				<option value="3">Bi-Weekly</option>
				<option value="4">Weekly</option>
				<option value="5">Daily</option>
			</form:select>
			<br/>
			Last Date :
			<form:input id="lastdatepicker" path="lastDate" readonly="true"></form:input>
			<br/>
			Time On Break :
			<form:input id="timeOnBreak" path="timeOnBreakStr" value="01:00" validationMessage="Enter the Time on Break" required="true"></form:input>
			<br/>
			<button id="primaryTextButton" type="submit"
				value="submitUserPayPeriod">Submit</button>
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
	
    $('#timeOnBreak').kendoMaskedTextBox({
        mask:'00:00'
    });

});
</script>
</html>