<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<%@ page isErrorPage="true" %>
				<!--in order to edit or update. allow us to render this view on a PUT request.-->
				<!-- in order to use validations via JSTL -->
				<!DOCTYPE html>
				<html>

				<head>
					<meta charset="ISO-8859-1">
					<title>View Product</title>
					<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
						rel="stylesheet"
						integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
						crossorigin="anonymous">
				</head>

				<body>

					<div class="container">
						<h1>${product.productName}</h1>
						<a href="/dashboard">Dashboard</a>

						<h2>Categories</h2>
						<ul>
							<c:forEach items="${product.categories}" var="category">
								<li>${category.categoryName}</li>
							</c:forEach>
						</ul>


						<form:form method="POST" action="/products/addCategory" modelAttribute="productCategory">
							<form:input type="hidden" path="product" value="${product.id}" />
							<div class="form-group row">
								<form:label path="category" class="col-sm-2 col-form-label">Add Category:</form:label>
								<form:errors path="category" />
								<div class="col-sm-10">
									<form:select class="form-control" path="category">
										<c:forEach items="${categoriesNotListed}" var="nonCat">
											<form:option value="${nonCat.id}">${nonCat.categoryName}</form:option>
										</c:forEach>
									</form:select>
									<form:errors class="small" path="category" />
								</div>

							</div>

							<button>Add</button>

						</form:form>



					</div>


				</body>

				</html>