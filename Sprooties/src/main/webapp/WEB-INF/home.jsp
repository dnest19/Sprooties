<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Home!</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
 <style>
body {
  background-image: url('/images/background.png');
  background-repeat: no-repeat;
}
</style> 
</head>
<body>
<div class="container overflow-hidden">
	<div class="row p-2 gy-4 bg-success">
		<div class="col-5">
			<h1>Your Home</h1>
		</div>
		<div class="col-2">
			<h4 class="p-3">Gold: ${user.gold}</h4>
		</div>
		<div class="col-sm">
			<a href="/journal" class="p-4 text-dark">Journal</a>
		</div>
		<div class="col-sm">
			<a href="/shop" class="p-4 text-dark">Shop</a>
		</div>
		<div class="col-sm">
			<a href="/logout" class="p-4 text-dark">Log Out</a>
		</div>

</div>
<br>
<br>
	<div class="row row-cols-2">


		
	    <c:forEach items="${items}" var="item">
	    		<div class="col">
	    			<img src="/images/${item.image }.png" class="img-fluid" alt="${item.image }">
	    		</div>
				
	    </c:forEach>
			<div class="col">
				<img src="/images/sprootycat.png" class="img-fluid" alt="Image not found">
			</div>
	</div>
  </div>
</body>
</html>