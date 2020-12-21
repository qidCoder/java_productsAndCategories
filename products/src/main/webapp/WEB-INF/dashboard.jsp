<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="ISO-8859-1">
			<title>Welcome to products</title>
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
				integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
				crossorigin="anonymous">
		</head>

		<body>
			<div class="container">
				<a href="/products/new">Add a new product</a>

				<a href="/categorys/create">Add a new category</a>

				<h1>All Products</h1>

				<table class="table table-dark">
					<thead>
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>Price</th>
							<th>Categories</th>

							<th>Actions</th>
						</tr>
					</thead>

					<tbody>
						<!-- loop through arraylist -->
						<c:forEach items="${allProducts}" var="product">
							<tr>
								<td><a href="/products/${product.id}">${product.productName}</a></td>
								<td>${product.description}</td>
								<td>${product.price}</td>
								<td>
									<c:forEach items="${product.categories}" var="cat">
										${cat.categoryName},
									</c:forEach>
								</td>
								<td><a href="/delete/${product.id}">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<h1>All Categories</h1>

				<table class="table table-dark">
					<thead>
						<tr>
							<th>Name</th>
							<th>Products</th>
							


							<th>Actions</th>
						</tr>
					</thead>

					<tbody>
						<!-- loop through arraylist -->
						<c:forEach items="${allCategorys}" var="category">
							<tr>
								<td><a href="/categorys/${category.id}">${category.categoryName}</a></td>
								<td>
									<c:forEach items="${category.products}" var="prod">
										${prod.productName},
									</c:forEach>
								</td>


								<td><a href="/deleteCategory/${category.id}">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>






			</div>
		</body>

		</html>