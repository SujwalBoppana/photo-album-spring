<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login </title>
</head>
<body>
<h3>${message}</h3><br>
<h2> login </h2>
<form action="login" method="post">
Enter userName :<input type="text" name="username">
Enter password : <input type="password" name="password">
<input type="submit">
</form>
</body>
</html>