<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<!-- in order to use validations via JSTL -->
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="ISO-8859-1">
				<title>Add new Product</title>
				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
					rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
					crossorigin="anonymous">
			</head>

			<body>

				<div class="container">
					<h1>New Product</h1>
					<a href="/dashboard">Dashboard</a>

					<form:form method="POST" action="/products/create" modelAttribute="product">
						<div class="form-group row">
							<form:label path="productName" class="col-sm-2 col-form-label">Product Name:</form:label>
							<form:errors path="productName" />
							<div class="col-sm-10">
								<form:input path="productName" class="form-control" />
							</div>

						</div>

						<div class="form-group row">
							<form:label path="description" class="col-sm-2 col-form-label">Description:</form:label>
							<form:errors path="description" />
							<div class="col-sm-10">
								<form:input path="description" class="form-control" />
							</div>

						</div>

						<div class="form-group row">
							<form:label path="price" class="col-sm-2 col-form-label">Price:</form:label>
							<form:errors path="price" />
							<div class="col-sm-10">
								<form:input path="price" class="form-control" />
							</div>

						</div>

						<button>Create</button>

					</form:form>

				</div>


			</body>

			</html>