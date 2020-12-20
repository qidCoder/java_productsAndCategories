<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!-- in order to use validations via JSTL -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new Category</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<h1>New Category</h1>	
		<a href="/dashboard">Dashboard</a>
		
		<form:form method="POST" action="/categorys/create" modelAttribute="category">
			<div class="form-group row">
				<form:label path="name" class="col-sm-2 col-form-label">Name:
					<form:errors path="name"/>
					<div class="col-sm-10">
						<form:input path="name" class="form-control"/>
					</div>
				</form:label>
			</div>
			
			<button>Create</button>
			
		</form:form>

	</div>


</body>
</html>