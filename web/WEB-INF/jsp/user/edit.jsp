<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
    <c:when test="${not empty user}">
		<c:set var="title" value="Редактирование пользователя"/>
    </c:when>
    <c:otherwise>
		<c:set var="title" value="Добавление пользователя"/>
        <jsp:useBean id="user" class="domain.User"/>
    </c:otherwise>
</c:choose>

<u:html title="${title}">
	<h2>${title}</h2>
	
	<c:url var="saveUrl" value="save.html"/>
	
	<div class="row">
    	<div class="col-md-4">    	
    		<form action="${saveUrl}" method="post">
    			<c:if test="${not empty user}">
    				<input type="hidden" name="id" value="${user.id}">
        		</c:if>
            
            	<div class="form-group">
					<label for="login" class="control-label">Логин:</label>
					<input type="text" id="login" name="login" value="${user.login}" class="form-control" />
				</div>
				
				<div class="form-group">
					<label for="password" class="control-label">Пароль:</label>
					<input type="password" id="password" name="password" class="form-control" />
				</div>
				
				<div class="form-group">
					<label for="role_id" class="control-label">Роль:</label>					
					<select class="select2 form-control" id="roleId" name="roleId">
        				<c:if test="${not empty user}">
        					<c:forEach var="role" items="${roles}">
        						<option value="${role.id}" <c:if test="${user.role.id == role.id}">selected</c:if>>${role.name}</option>           	
        					</c:forEach> 
        				</c:if>
        
        				<c:if test="${empty user}">
        					<option selected="selected">
        					<c:forEach var="role" items="${roles}">
        						<option value="${role.id}">${role.name}</option> 
        					</c:forEach>
        				</c:if>             
        			</select>
				</div>
				
				<div class="form-group">
                	<input type="submit" value="Сохранить" class="btn btn-default" />
            	</div>
        		<a href="index.html">Назад</A>
    		</form>
    	</div>
    </div>
</u:html>