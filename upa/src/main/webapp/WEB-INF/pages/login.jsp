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

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<title>Log in with your account</title>

<body>

	<form>
		<h2 class="form-heading">Log in</h2>
		<span>${message}</span>
		<input name="username" type="text" class="form-control" placeholder="Username" autofocus="true"/>
		<input name="password" type="password" class="form-control" placeholder="Password"/>
		<span>${error}</span>
		<button class="" type="submit">Log In</button>
	</form>
</body>


</html>
