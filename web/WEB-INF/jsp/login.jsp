<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="Авторизация">
	<h1>Авторизация</h1>
	<c:if test="${not empty message}">
		<div class="alert alert-danger alert-dismissible" role="alert">
        	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        	${message}
    	</div>
	</c:if>
	<c:url var="url" value="/login.html"/>
		
	<div class="row">
    	<div class="col-md-4">
			<form action="${url}" method="post">				
				<div class="form-group">
					<label for="login" class="control-label">Логин:</label>
					<input type="text" id="login" name="login" class="form-control" />
				</div>
					
				<div class="form-group">
					<label for="password" class="control-label">Пароль:</label>
					<input type="password" id="password" name="password" class="form-control" />
				</div>
				
				<div class="form-group">
                	<input type="submit" value="Войти" class="btn btn-default" />
            	</div>
			</form>
		</div>
	</div>		
</u:html>