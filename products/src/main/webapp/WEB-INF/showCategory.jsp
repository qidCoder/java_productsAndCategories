<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<%@ page isErrorPage="true" %>  <!--in order to edit or update. allow us to render this view on a PUT request.-->
			<!-- in order to use validations via JSTL -->
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="ISO-8859-1">
				<title>View Category</title>
				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
					rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
					crossorigin="anonymous">
			</head>

			<body>

				<div class="container">
					<h1>${category.categoryName}</h1>
					<a href="/dashboard">Dashboard</a>

					<h2>Products</h2>





				</div>


			</body>

			</html>