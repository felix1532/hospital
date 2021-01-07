<%@page errorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:url var="homeUrl" value="/index.html"/>

<u:html title="Ошибка">
	<h1>Ошибка</h1>
	<p style="color: red;">
		В приложении возникла ошибка.<br>
		${pageContext.exception.message}
	</p>
	<p><a href="${homeUrl}">на главную</a></p>
</u:html>