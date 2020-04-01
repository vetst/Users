<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>Удалить пользователя</h2>
 <a href="main"><<< вернуться назад</a>
		<c:if test = "${message != null }">
         <p><c:out value = "${message}"/><p>
		 	 <form action="delete" method="POST">
		 	 <input type="hidden" name="userId" value="${userId}">
             <input type="hidden" name="value" value="1">
             <input type ="submit" value ="Удалить" /></form>
      </c:if>
	   <c:if test="${userIdRedirect != null}">
		<c:redirect url = "http://localhost:8080/web5/main"/>
	</c:if>
</body>
</html>