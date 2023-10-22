<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search User</title>
</head>
<body>

<h2>Search Result</h2>
<a href="listusers2">Deleted Users</a> | 
<a href="signup">Add new user</a>
<br><br>
<c:forEach items="${users}" var="u">

${u.userId } |	${u.firstName } | <a href="deleteuser?userId=${u.userId}">Delete</a>
|
<a href="deleteuser2?userId=${u.userId}">Delete2</a>
<br>
</c:forEach>


</body>
</html>