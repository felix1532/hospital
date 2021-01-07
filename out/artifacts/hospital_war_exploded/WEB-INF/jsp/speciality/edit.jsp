<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
	<c:when test="${not empty speciality}">
		<c:set var="title" value="Редактирование специальности" />
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Добавление специальности" />
		<jsp:useBean id="speciality" class="domain.Speciality" />
	</c:otherwise>
</c:choose>

<u:html title="${title}">
	<h2>${title}</h2>

	<c:url var="saveUrl" value="save.html" />
	<div class="row">
		<div class="col-md-4">
			<form action="${saveUrl}" method="post">
				<c:if test="${not empty speciality}">
					<input type="hidden" name="id" value="${speciality.id}">
				</c:if>
				
				<div class="form-group">
					<label for="name" class="control-label">Название:</label>
					<input type="text" id="name" name="name" value="${speciality.name}" class="form-control" />
				</div>
				
				<div class="form-group">
					<label for="doctorsCount" class="control-label">Кол-во врачей:</label>
					<input type="number" id="doctorsCount" name="doctorsCount" value="${speciality.doctorsCount}" class="form-control" />
				</div>


				<c:if test="${speciality.isNarrowSpecialist}">
					<c:set var="checked" value="checked"/>
				</c:if>
				<div class="form-group">
					<label for="isNarrow" class="control-label">Экзамен:</label>
					<input type="checkbox" id="isNarrow" name="isNarrow" value="value" ${checked} class="form-control" />
				</div>

				
				<div class="form-group">
					<label for="salaryExpenses" class="control-label">Расходы на зарплату:</label>
					<input type="number" id="salaryExpenses" name="salaryExpenses" value="${speciality.salaryExpenses}" class="form-control" />
				</div>
				
				<div class="form-group">
					<label for="wageRate" class="control-label">Зарплатная ставка:</label>
					<input type="number" id="wageRate" name="wageRate" value="${speciality.wageRate}" class="form-control" />
				</div>

				<div class="form-group">
					<input type="submit" value="Сохранить" class="btn btn-default" />
				</div>
				<a href="index.html">Назад</A>
			</form>
		</div>
	</div>
</u:html>