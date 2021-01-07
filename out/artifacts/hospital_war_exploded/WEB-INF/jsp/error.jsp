<%@page errorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<c:url var="homeUrl" value="/index.html"/>

<u:html title="Ошибка">
	<h1>Ошибка</h1>
	<c:choose>
		<c:when test="${not empty message}">
			<p style="color: red;">${message}</p>
		</c:when>
		<c:when test="${not empty pageContext.exception}">
			<p style="color: red;">
				Проблемы с сервером. Обратитесь к системному администратору.<br>
			</p>
		</c:when>
		<c:otherwise>
			<p style="color: red;">Непредвиденная ошибка приложения</p>
		</c:otherwise>
	</c:choose>
	<p><a href="${homeUrl}">на главную</a></p>
</u:html>