<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>edit</title>
</head>
<body>
<img src="data:image/png;base64,${photos.encoder}" width="400" height="400" alt="Image">

<form action="updateImage" method="post" enctype="multipart/form-data">
<pre>

	<input type="hidden" name="pk_id" value="${photos.id}">
	Photo: <input type="file" name="photo"> <br>
	<input type="submit" value="Submit">
</pre>

</form>

</body>
</html>