<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h1 align="center">Book Finder Web Application</h1>
<body>

	<div align="center">
		<h1>Search</h1>
		<form:form method="post" modelAttribute="searchForm" action="search">
			<table>
				<tr>
					<td><form:label path="author">Author</form:label></td>
					<td><form:input path="author" type="text" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Search" /></td>
				</tr>

			</table>

		</form:form>
	</div>


	<div align="center">
		<table border="0">
			<tr>
				<td colspan="2" align="center"><h2>Search Results</h2></td>
			</tr>

			<c:if test="${not empty booksByAuthor}">
				<ul>
					<c:forEach var="book" items="${booksByAuthor}">
						<tr>
							<td>ISBN:</td>
							<td>${book.isbn}</td>
						</tr>
						<tr>
							<td>Title:</td>
							<c:url
								value="http://www.amazon.co.uk/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords=${book.title}"
								var="url" />
							<td><a href="<c:out value='${url}'/>">${book.title}</a></td>
						</tr>
					</c:forEach>
				</ul>
			</c:if>

		</table>
	</div>
</body>

</html>