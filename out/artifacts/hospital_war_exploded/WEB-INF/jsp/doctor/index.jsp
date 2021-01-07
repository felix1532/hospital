<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Список докторов">
	<br />
	<form method="get">
		<div class="form-group">
			<label for="specialityId" class="control-label">Выбор специальности:</label>
			<select class="select2 form-control" id="specialityId" name="specialityId">
				<c:if test="${not empty speciality}">
					<c:forEach var="_speciality" items="${specialities}">
						<option value="${_speciality.id}" <c:if test="${speciality.id == _speciality.id}">*(selected)
				</c:if>>${_speciality.name}</option>
				</c:forEach>
				</c:if>
			</select>
		</div>

		<div class="form-group">
			<input type="submit" value="Фильтр" class="btn btn-default" />
		</div>
	</form>

	<c:set var="ifSecretaryRole" value="${currentUser.role.name == 'secretary'}"></c:set>

	<c:if test="${not empty speciality}">
		<h1>Список докторов специальности ${speciality.name}</h1>
	</c:if>

	<c:if test="${ifSecretaryRole == true}">
		<a href="edit.html
			<c:if test="${not empty speciality}">?specialityId=${speciality.id}</c:if>">
			Добавить врача
		</a>
	<br /><br />
	</c:if>
	
	<c:if test="${not empty currentSort}">
        ${currentSort}
    </c:if>

	<c:choose>
		<c:when test="${not empty doctors}">
			<form action="delete.html" method="post">
				<table>
					<tr>
						<c:if test="${ifSecretaryRole == true}">
							<th>&nbsp;</th>
						</c:if>

						<th>
<%--							<a id="titleSort" href="index.html?sortState=${titleSort}--%>
							<c:if test="${not empty faculty}">&facultyId=${faculty.id}"></c:if>
								Фамилия
<%--							</a>--%>
						</th>

						<th>
<%--							<a id="fullNameSort" href="index.html?sortState=${fullNameSort}--%>
							<c:if test="${not empty faculty}">&facultyId=${faculty.id}"></c:if>
								Имя
<%--							</a>--%>
						</th>

						<th>
<%--							<a id="phoneNumberSort" href="index.html?sortState=${phoneNumberSort}--%>
							<c:if test="${not empty faculty}">&facultyId=${faculty.id}"></c:if>
								Отчество
<%--							</a>--%>
						</th>

						<th>
<%--							<a id="cabinetNumberSort" href="index.html?sortState=${cabinetNumberSort}--%>
							<c:if test="${not empty faculty}">&facultyId=${faculty.id}"></c:if>
								Номер кабинета
<%--							</a>--%>
						</th>

						<th>
<%--							<a id="teachersCountSort" href="index.html?sortState=${teachersCountSort}--%>
							<c:if test="${not empty faculty}">&facultyId=${faculty.id}"></c:if>
								Кол-во преподавателей
<%--							</a>--%>
						</th>

						<th>
<%--							<a id="releaseSort" href="index.html?sortState=${releaseSort}--%>
							<c:if test="${not empty faculty}">&facultyId=${faculty.id}"></c:if>
								Выпускающая
<%--							</a>--%>
						</th>

						<th>
<%--                            <a id="dateOfCreateSort" href="index.html?sortState=${dateOfCreateSort}--%>
                            <c:if test="${not empty faculty}">&facultyId=${faculty.id}"></c:if>
								Дата образования
<%--							</a>--%>
                        </th>

						<th></th>
					</tr>

					<c:forEach var="doctor" items="${doctors}">
						<c:url var="editUrl" value="edit.html">
							<c:param name="id" value="${doctor.id}" />
						</c:url>
						<tr>
							<c:if test="${ifSecretaryRole == true}">
								<td>
									<input type="checkbox" name="id" value="${doctor.id}">
								</td>
							</c:if>

							<td>${doctor.lastName}</td>
							<td>${doctor.firstName}</td>
							<td>${doctor.patronymic}</td>
							<td>${doctor.lotNumber}</td>
							<td>${doctor.birthdayDate}</td>
							<td>${doctor.employmentDate}</td>
							<td>${doctor.salary}</td>

							<td>
								<c:if test="${ifSecretaryRole == true}">
									<a href="${editUrl}">Редактировать</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<p>
					<c:if test="${ifSecretaryRole == true}">
						<br />
						<c:if test="${not empty speciality}">
							<input type="hidden" name="specialityId" value="${speciality.id}">
						</c:if>
						<input type="submit" value="Удалить" class="btn btn-default" />
					</c:if>
				</p>
			</form>
		</c:when>
		<c:otherwise>
			<p>Список докторов специальности пуст</p>
		</c:otherwise>
	</c:choose>
</u:html>