<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Index - Telephone Directory</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@  include file="fragments/headerIncludes.jspf"%>
</head>
<body>
	<h2 id="top">Telephone Directory</h2>
	<fmt:setBundle basename="messages.messages"/>
	<fmt:message key="welcome" />
	
	<%@  include file="fragments/navigation.jspf"%>

	<h3>Index</h3>

	<section id="search">
		<%@ include file="fragments/searchForm.jspf"%>
	</section>
	<br />
	<section id="list">
		<form id="delete-selected-form" action="/deleteSelected" method="post">
			<input type="submit" id="delete-selected" value="Delete selected" />

			<table id="index">
				<tr>
					<th></th>
					<th><input type="checkbox" id="select-all" /></th>
					<th>Name</th>
					<th>Telephone</th>
				</tr>
				<tbody>
					<c:forEach var="contact" items="${contacts}">

						<tr>
							<td>
								<a href="/contact/${contact.id}">Details</a> <a
									href="/update/${contact.id}">Edit</a> <a
									href="/delete/${contact.id}">Delete</a>
							</td>
							<td>
								<input type="checkbox" name="selectedId" value="${contact.id}" />
							</td>
							<td>
								<label><a href="/contact/${contact.id}">${contact.name}</a></label>
							</td>
							<td>
								<label>${contact.phone}</label>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>

		<c:if test="${empty contacts}">
			<p>No contacts found.</p>
		</c:if>

	</section>
	
	<a id="scroll-to-top" href="#top" title="Scroll to top">Top</a>
	
	<%@ include file="fragments/dialogs.jspf"%>
</body>
</html>