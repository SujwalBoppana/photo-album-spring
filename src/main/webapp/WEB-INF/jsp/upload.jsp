<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload file</title>
</head>
<body>
<form action="InsertImage" method="post" enctype="multipart/form-data">
<pre>
	Photo: <input type="file" name="photos"  multiple > <br>
	<input type="submit" value="Submit">
</pre>
</form>
   <h3>${message}</h3><br>
</body>
</html>