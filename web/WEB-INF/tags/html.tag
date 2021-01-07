<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" rtexprvalue="true" required="true" type="java.lang.String" %>
<%@ attribute name="scripts" rtexprvalue="true" required="false" type="java.lang.String" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Project : ${title}</title>

		<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/css/select2.min.css" rel="stylesheet" />
		<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/js/select2.min.js"></script>

		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/site.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/site.css" />		
	</head>

	<body>
		<div class="container body-content">
			<u:menu/>
			<jsp:doBody/>
			<br/>
			<div>&copy; Project - 2020</div>
		</div>
		${scripts}
	</body>
</html>
