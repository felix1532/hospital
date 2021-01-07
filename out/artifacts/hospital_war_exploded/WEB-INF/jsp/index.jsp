<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="Домашняя">
	<jsp:attribute name="scripts">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/site.js"></script>
	</jsp:attribute>
	
	<jsp:body>
	<h1>Домашняя страница</h1>
	<h3>Hello Steve Jans!</h3>
	<c:url var="url" value="/login.html"/>
		
	<c:if test="${empty currentUser}">
		<h3><a href="${url}">Авторизация</a></h3>
	</c:if>		
	</jsp:body>
</u:html>