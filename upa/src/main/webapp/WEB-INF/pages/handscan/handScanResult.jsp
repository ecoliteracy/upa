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

	<a href="index">Go Back to Index Menu</a>

	<ul class="fieldlist">
		<li><label class="k-label">From </label> <input class="k-label"
			value="${handscanheader.firstDate}" readonly disabled /></li>
		<li><label class="k-label">To </label> <input class="k-label"
			value="${handscanheader.lastDate}" readonly disabled /></li>
		<li><label class="k-label">Total Hours </label> <input
			class="k-label" value="${handscanheader.totalParticipationInMin}"
			readonly disabled /></li>
	</ul>
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
							scanOutTime : { type : "date" },
							participationTime : {type : "date"}
						}
					}
				},
				pageSize : 20, 
				  serverSorting: true,
				  sort: { scanDate: "age", dir: "desc" }
	
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
			            {field : "scanDate", title : "Scan Date", format: "{0:MM/dd/yyyy}", width : "100px"},
			            {field : "scanInTime", title : "Scan In", format:"{0:HH:mm}" , width : "100px"},
			            {field : "scanOutTime", title : "Scan Out",format:"{0:HH:mm}", width : "100px"},
			            {field : "participationTime", title : "Hours",format:"{0:HH:mm}", width : "80px"}]
		});
	
	});
</script>
</body>
</html>