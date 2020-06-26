<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Contact Details - Telephone Directory</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@  include file="fragments/headerIncludes.jspf"%>
</head>
<body>
	<h2>Telephone Directory</h2>

	<%@  include file="fragments/navigation.jspf"%>

	<h3>Contact Details</h3>

	<section id="search">
		<%@ include file="fragments/searchForm.jspf" %>
	</section>
	<br />

	<section id="contact-info">
		<table id="index">
			<tr>
				<td>
					<a href="/update/${contact.id}">Edit</a>
					<a href="/delete/${contact.id}">Delete</a>
				</td>
			</tr>
			<tr>
				<td>ID: ${contact.id}</td>
			</tr>
			<tr>
				<td>${contact.name}</td>
			</tr>
			<tr>
				<td>${contact.phone}</td>
			</tr>
		</table>
	</section>
	
	<%@ include file="fragments/dialogs.jspf"%>
</body>
</html>