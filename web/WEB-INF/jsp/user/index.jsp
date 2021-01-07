<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="Список пользователей">
	<h1>Список пользователей</h1>
	<a href="edit.html">Добавить пользователя</a>
	<br/><br/>
	
	<c:choose>
		<c:when test="${not empty users}">		
			<form action="delete.html" method="post">
				<table>
					<tr>
						<th>&nbsp;</th>
						<th>
							<a id="loginSort" href="index.html?sortState=${loginSort}">Логин</a>
						</th>
						<th>
							<a id="roleSort" href="index.html?sortState=${roleSort}">Роль</a>
						</th>
						<th></th>
					</tr>
					<c:forEach var="user" items="${users}">		
						<c:url var="editUrl" value="edit.html">
							<c:param name="id" value="${user.id}"/>
						</c:url>		
						<tr>
							<td>
								<input type="checkbox" name="id" value="${user.id}">
							</td>		
							<td>${user.login}</td>
							<td>${user.role.name}</td>				
							<td><a href="${editUrl}">Редактировать</a></td>
						</tr>
					</c:forEach>
				</table>
				<p>
					<br/>
			       	<input type="submit" value="Удалить" class="btn btn-default" />
			       </p>
			</form>
		</c:when>
		<c:otherwise>
			<p>Список пользователей пустой</p>
		</c:otherwise>
	</c:choose>
</u:html>