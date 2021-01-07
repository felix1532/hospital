<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="Список специальностей">
    <c:set var="ifSecretaryRole" value="${currentUser.role.name == 'secretary'}"></c:set>

    <h1>Список специальностей</h1>
    
    <c:if test="${ifSecretaryRole == true}">
        <a href="edit.html">Добавить специальность</a>
        <br /><br />
    </c:if>
    
    <c:if test="${not empty currentSort}">
        ${currentSort}
    </c:if>

    <c:choose>
        <c:when test="${not empty specialities}">
            <form action="delete.html" method="post">
                <table>
<%--                    <tr>--%>
<%--                        <c:if test="${ifSecretaryRole == true}">--%>
<%--                            <th>&nbsp;</th>--%>
<%--                        </c:if>--%>
<%--                                            --%>
<%--                        <th>--%>
<%--							<a id="titleSort" href="index.html?sortState=${titleSort}">Название</a>--%>
<%--						</th>	--%>
<%--						<th>--%>
<%--                            <a id="fullNameSort" href="index.html?sortState=${fullNameSort}">ФИО</a>--%>
<%--                        </th>  				--%>
<%--						<th>--%>
<%--							<a id="phoneNumberSort" href="index.html?sortState=${phoneNumberSort}">Номер телефона</a>--%>
<%--						</th>--%>
<%--						<th>--%>
<%--							<a id="dekanatCabinetSort" href="index.html?sortState=${dekanatCabinetSort}">Номер кабинета деканата</a>--%>
<%--						</th>--%>
<%--						<th>--%>
<%--							<a id="studentsCountSort" href="index.html?sortState=${studentsCountSort}">Кол-во студентов</a>--%>
<%--						</th>--%>
<%--						<th>--%>
<%--							<a id="dateOfCreateSort" href="index.html?sortState=${dateOfCreateSort}">Дата образования</a>--%>
<%--						</th>--%>
<%--                        <th></th>--%>
<%--                    </tr>--%>
                    <c:forEach var="speciality" items="${specialities}">
                        <c:url var="editUrl" value="edit.html">
                            <c:param name="id" value="${speciality.id}" />
                        </c:url>
                        <tr>
                            <c:if test="${ifSecretaryRole == true}">
                                <td>
                                    <input type="checkbox" name="id" value="${speciality.id}">
                                </td>
                            </c:if>

							<td>${speciality.name}</td>
                            <td>${speciality.doctorsCount}</td>
                            <td>${speciality.isNarrowSpecialist}</td>
							<td>${speciality.salaryExpenses}</td>
							<td>${speciality.wageRate}</td>

                            <td>
                                <c:if test="${ifSecretaryRole == true}">
                                    <a href="${editUrl}">Редактировать</a> |
                                </c:if>
                                <a href="../doctor/index.html?specialityId=${speciality.id}">Список отделений</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <p>
                    <c:if test="${ifSecretaryRole == true}">
                        <br />
                        <input type="submit" value="Удалить" class="btn btn-default" />
                    </c:if>
                </p>
            </form>
        </c:when>
        <c:otherwise>
            <p>Список специальностей пустой</p>
        </c:otherwise>
    </c:choose>
</u:html>