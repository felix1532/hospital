<%@page errorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="homeUrl" value="/index.html"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>404</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
	</head>

	<body>
		<div class="container body-content">		
			<h1>Ошибка 404</h1>
	
			<c:if test="${not empty pageContext.errorData.requestURI}">
				<p style="color: red;">Запрошенная страница ${pageContext.errorData.requestURI} не найдена на сервере</p>
			</c:if>
			
			<p><a href="${homeUrl}">на главную</a></p>
			<br/>
			<div>&copy; Project - 2020</div>
		</div>
	</body>
</html>