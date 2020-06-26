<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Edit the contact - Telephone Directory</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@  include file="fragments/headerIncludes.jspf"%>
</head>
<body>
	<h2>Telephone Directory</h2>
	
	<%@ include file="fragments/navigation.jspf" %>
	
	<h3>Edit the contact</h3>

	<section id="search">
		<%@ include file="fragments/searchForm.jspf" %>
	</section>
	<br />
	<section id="edit-record">
		<spring-form:form commandName="contact" method="POST">
			<spring-form:errors path="*" cssClass="errorBlock" element="div" />
			
			<input type="hidden" id="id" name="id" value="${contact.id}" />
			<table>
				<tr>
					<td>Name</td>
					<td><spring-form:input path="name" cssErrorClass="errorBlock" autofocus="on" /></td>
					<td><spring-form:errors path="name" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Telephone</td>
					<td><spring-form:input path="phone" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="phone" cssClass="error" /></td>

				</tr>
				<tr>
					<td colspan="3">
						<spring-form:button>Save</spring-form:button>
					</td>
				</tr>
			</table>
		</spring-form:form>
	</section>
	
	<%@ include file="fragments/dialogs.jspf"%>
</body>
</html>