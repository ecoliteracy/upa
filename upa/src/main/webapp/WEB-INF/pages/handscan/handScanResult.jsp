<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
html {
	font-size: 14px;
	font-family: Arial, Helvetica, sans-serif;
}
</style>
<title>Spring 4 MVC - HelloWorld Index Page</title>
<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2018.2.516/styles/kendo.common-material.min.css" />
<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2018.2.516/styles/kendo.material.min.css" />
<link rel="stylesheet"	href="https://kendo.cdn.telerik.com/2018.2.516/styles/kendo.material.mobile.min.css" />
<script src="https://kendo.cdn.telerik.com/2018.2.516/js/jquery.min.js"></script>
<script	src="https://kendo.cdn.telerik.com/2018.2.516/js/kendo.all.min.js"></script>	
<script type="text/javascript" src="resources/js/app.js"></script>
</head>
<body>

	<p>Status: ${msg}</p>
	<p>From ${handscanheader.firstDate}</p>
	<p>To ${handscanheader.lastDate}</p>
	<br>
	<a href="index">Index Menu</a>
	<br>
	<c:forEach items="${handscanheader.handscanrecords}"
		var="handscanrecord">
		<tr>
			<td>${handscanrecord.recordSeq}</td>
		</tr>
	</c:forEach>
	
	<div id="example">
	<div id="grid"></div>
	
<script>
			$(document).ready(function() {
				var handscans = ${handscanrecordsAsJson};

				$("#grid").kendoGrid({
					dataSource : {
						data : handscans,
						schema : {
							model : {
								fields : {
									recordSeq   : { type : "number"},
									scanDate: { type: "date", parse: function(value) { return new Date(value); }},
									scanInTime  : { type : "date" },
									scanOutTime : { type : "date" }
								}
							}
						},
						pageSize : 20
					},
					height : 550,
					scrollable : true,
					sortable : true,
					filterable : true,
					pageable : {
						input : true,
						numeric : false
					},
					columns : [ {field : "recordSeq",title : "No",width : "60px"},
					            {field : "scanDate", title : "Scan Date", format: "{0:MM/dd/yyyy}"},
					            {field : "scanInTime", title : "Scan In", format:"{0:HH:mm}"},
					            {field : "scanOutTime", title : "Scan Out",format:"{0:HH:mm}"}]
				});

			});
		</script>
		</div>
</body>
</html>