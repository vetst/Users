<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
	 <h2>Добавить пользователя</h2>
     <br>
	 <form action="add" method="POST">
     <p>Введите имя: <input type="text"  name = "name"></p>
     <p>Введите фамилию: <input type="text"  name = "surName"></p>
     <input type ="submit" value ="Добавить" /></form>
        <c:if test="${value != null}">
     		<c:redirect url = "http://localhost:8080/web5/main"/>
     	</c:if>
</body>
</html>