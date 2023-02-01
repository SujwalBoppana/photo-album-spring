<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Album</title>
</head>
<body>
<h3><a href="upload">Upload new File</a></h3><br>
 <c:forEach var="photos" items="${image}"><br> 
    <img src="data:image/png;base64,${photos.encoder}" width="400" height="400" alt="Image"><br>
    <a href="editimg/${photos.id}">Edit</a>
  <a href="deleteimg/${photos.id}">Delete</a>
    </c:forEach>
</body>
</html>