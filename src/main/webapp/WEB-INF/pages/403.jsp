<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>
	<h1>HTTP Status 403 - Доступ запрещен</h1>

	<c:choose>
		<c:when test="${empty username}">
			<h2>Вы не имеете доступа к этой странице!</h2>
		</c:when>
		<c:otherwise>
			<h2>Пользователь : ${username} <br/>Вы не имеете доступа к этой странице!</h2>
		</c:otherwise>
	</c:choose>

</body>
</html>