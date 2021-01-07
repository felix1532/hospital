<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
	<c:when test="${not empty doctor}">
		<c:set var="title" value="Редактирование отделения"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Добавление отделения"/>
		<jsp:useBean id="doctor" class="domain.Doctor"/>
	</c:otherwise>
</c:choose>

<u:html title="${title}">
	<h2>${title}</h2>

	<c:url var="saveUrl" value="save.html" />
	
	<div class="row">
  		<div class="col-md-4">	  	
    		<form action="${saveUrl}" method="post">
    			<c:if test="${not empty doctor}">
    				<input type="hidden" name="id" value="${doctor.id}">
        		</c:if>
        		
        		<div class="form-group">
					<label for="lastName" class="control-label">Фамилия:</label>
					<input type="text" id="lastName" name="lastName" value="${doctor.lastName}" class="form-control" />
				</div>
						
				<c:if test="${empty speciality}">
					<div class="form-group">
						<label for="specialityId" class="control-label">Специальности:</label>
						<select class="select2 form-control" id="specialityId" name="specialityId">
        					<c:if test="${not empty doctor}">
        						<c:forEach var="speciality" items="${specialities}">
        							<option value="${speciality.id}" <c:if test="${doctor.speciality.id == speciality.id}">*(selected)</c:if>>${speciality.name}</option>
        						</c:forEach> 
        					</c:if> 
        				</select>
					</div>
				</c:if>
				
				<c:if test="${not empty speciality}">
					<input type="hidden" name="specialityId" value="${speciality.id}">
					<c:url var="returnUrl" value="/doctor/index.html" context="/" >
                    	<c:param name="specialityId" value="${speciality.id}" />
                    </c:url>			
					<input type="hidden" name="returnUrl" value="${returnUrl}">
				</c:if>

				<div class="form-group">
					<label for="firstName" class="control-label">Имя:</label>
					<input type="text" id="firstName" name="firstName" value="${doctor.firstName}" class="form-control" />
				</div>

				<div class="form-group">
					<label for="patronymic" class="control-label">Отчество:</label>
					<input type="text" id="patronymic" name="patronymic" value="${doctor.patronymic}" class="form-control" />
				</div>
				
				<div class="form-group">
					<label for="lotNumber" class="control-label">Номер участка:</label>
					<input type="number" id="lotNumber" name="lotNumber" value="${doctor.lotNumber}" class="form-control" />
				</div>
				
				<div class="form-group">
					<label for="birthdayDate" class="control-label">Дата рождения:</label>
					<input type="date" id="birthdayDate" name="birthdayDate" value="${doctor.birthdayDate}" class="form-control" />
				</div>
				
				<div class="form-group">
					<label for="employmentDate" class="control-label">Дата приёма на работу:</label>
					<input type="date" id="employmentDate" name="employmentDate" value="${doctor.employmentDate}" class="form-control" />
				</div>
				
<%--				<c:if test="${chair.release}">--%>
<%--                	<c:set var="checked" value="checked"/>--%>
<%--            	</c:if>--%>
<%--				--%>
<%--				<div class="form-group">--%>
<%--					<label for="release" class="control-label">Выпускающая:</label>--%>
<%--					<input type="checkbox" id="release" name="release" value="value" ${checked} class="form-control" />--%>
<%--				</div>--%>
				
				<div class="form-group">
					<label for="salary" class="control-label">Зарплата:</label>
					<input type="number" id="salary" name="salary" value="${doctor.salary}" class="form-control" />
				</div>
				
				<div class="form-group">
                	<input type="submit" value="Сохранить" class="btn btn-default" />
            	</div>
        		<a href="index.html">Назад</A>
    		</form>
    	</div>
    </div>
</u:html>