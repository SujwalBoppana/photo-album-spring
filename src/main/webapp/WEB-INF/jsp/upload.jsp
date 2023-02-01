<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload file</title>
</head>
<body>
<form action="InsertImage" method="post" enctype="multipart/form-data">
<pre>
	Photo: <input type="file" name="photos" accept="image/png, image/jpeg" multiple required="required"> <br>
	<input type="submit" value="Submit">
</pre>
</form>

</body>
</html>