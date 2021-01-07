<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="homeUrl" value="/index.html"/>
<c:url var="specialityUrl" value="/speciality/index.html"/>

<div style="background: #ca9b73">
	<ul>
		<li><a href="${homeUrl}">Домой</a></li>
		<c:if test="${empty currentUser}">
			<c:url var="loginUrl" value="/login.html"/>
			<li><a href="${loginUrl}">Войти</a></li>
		</c:if>
		<li><a href="${specialityUrl}">Специальности</a></li>
		<c:forEach var="item" items="${menu}">
			<c:url var="url" value="${item.url}"/>
			<li><a href="${url}">${item.name}</a></li>
		</c:forEach>
	</ul>
</div>

<!--<c:if test="${not empty menu}">-->
<!--</c:if>-->

<c:if test="${not empty currentUser}">
	<c:url var="logoutUrl" value="/logout.html"/>
	<div>${currentUser.login} (<a href="${logoutUrl}">выход</a>)</div>
</c:if>
