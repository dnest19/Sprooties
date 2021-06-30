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
			<h1>Sprooties Journal</h1>
		</div>
		<div class="col-2">
			<h4 class="p-3">Gold: ${user.gold}</h4>
		</div>
		<div class="col-sm">
			<a href="/shop" class="p-4 text-dark">Shop</a>
		</div>
		<div class="col-sm">
			<a href="/home" class="p-4 text-dark">Home</a>
		</div>
		<div class="col-sm">
			<a href="/logout" class="p-4 text-dark">Log Out</a>
		</div>

	</div>
  <div class="row p-4">
    <div class="col border-bottom border-sucess p-1 ">
			<form:form action="/post/create" method="POST" modelAttribute="post">  
			<form:input type="hidden" value="${user.id}" path="user"/>
			<div class="form-group">
			<form:label path="postText">What are you grateful for today?: </form:label>
			<form:errors path="postText"/>
			<form:textarea path="postText"/>
		</div>
			<button class="btn btn-primary">Submit</button>
		</form:form>


    	</div>
    	
    	    </div>
		<div class="row">
			<div class="col">
				<div class="row">
				<p class="alert alert-primary" role="alert">Need help? Here's a prompt:</p>
				</div>
				<div class="row">
				<p class="alert alert-success" role="alert">${prompt }</p>
				</div>
				<div class="row">
					<a class="btn btn-info" href="/journal" role="button">New Prompt</a>
				</div>
			</div>
			<div class="col">
				<img src="/images/sprootycat.png" class="img-fluid" alt="Image not found">
			</div>
		</div>
	<div class="col">
		<h3>Journal History</h3>
	    <c:forEach items="${posts}" var="post">
	    	<div class="row">
	    		<div class="col border border-dark">
	    			<p>Journal Entry Posted On: ${post.createdAt}<p/>
	    			<p class="bg-info">${post.postText}</p>
	    		</div>
	    	</div>
	    </c:forEach>
	</div>

  </div>
</body>
</html>