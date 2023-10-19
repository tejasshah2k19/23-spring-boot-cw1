<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List User</title>
</head>
<body>

<c:forEach items="${users}" var="u">

${u.userId } |	${u.firstName } | <a href="deleteuser?userId=${u.userId}">Delete</a><br>
</c:forEach>


</body>
</html>