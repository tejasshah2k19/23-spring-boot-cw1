<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Upload Profile Pic</h2>
<form action="saveprofile" method="post" enctype="multipart/form-data">

Profile : <input type="file" name="profile"/><br><br>
<input type="submit" value="Upload Profile"/>
</form>
</body>
</html>