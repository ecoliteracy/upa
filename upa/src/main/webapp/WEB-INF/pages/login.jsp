<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:h="http://java.sun.com/jsf/html">

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<head>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<script type="text/javascript" src="resources/js/app.js"></script>

</head>

<title>Log in with your account</title>

<body>
	<div>
	<form>
		<h2>Log in</h2>
		<span>${message}</span>
		<input name="username" type="text" class="form-control" placeholder="Username" autofocus="true"/>
		<input name="password" type="password" class="form-control" placeholder="Password"/>
		<span>${error}</span>
		<button class="" type="submit">Log In</button>
	</form>
	</div>
</body>


</html>
