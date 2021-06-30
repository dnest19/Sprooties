<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sprooties</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

</head>
<body>
<div class="container overflow-hidden">
	<div class="row p-2 gy-4 bg-success">
		<div class="col-5">
			<h1>Sprooties Shop</h1>
		</div>
		<div class="col-2">
			<h4 class="p-3">Gold: ${user.gold}</h4>
		</div>
		<div class="col-sm">
			<a href="/journal" class="p-4 text-dark">Journal</a>
		</div>
		<div class="col-sm">
			<a href="/home" class="p-4 text-dark">Home</a>
		</div>
		<div class="col-sm">
			<a href="/logout" class="p-4 text-dark">Log Out</a>
		</div>

</div>
		<h3>Items</h3>
	<div class="row row-cols-2">


		
	    <c:forEach items="${items}" var="item">
	    		<div class="col border border-dark p-1">
	    			<img src="/images/${item.image }.png" class="img-fluid" alt="${item.image }">
	    			<p class="bg-warning">Gold Cost: ${item.cost}</p>
	    			<a class="btn btn-info" href="/shop/${item.id }/buy" role="button">Buy Item</a>
	    		</div>

	    </c:forEach>

	</div>
  </div>
</body>
</html>