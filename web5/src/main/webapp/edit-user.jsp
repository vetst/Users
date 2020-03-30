<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
 <h2>Обновить пользователя</h2><br>
 <form action="edit" method="GET" >
 <input type="hidden" name="userId" value="${userId}">
 <p>Введите имя: <input type="text" value = "${name}" name = "name"></p>
 <p>Введите фамилию: <input type="text" value = "${surName}" name = "surName"></p>
 <input type ="submit" value ="Обновить" /></form>
         <c:if test="${value != null}">
      		<c:redirect url = "http://localhost:8080/web5/main"/>
      	</c:if>
</body>
</html>