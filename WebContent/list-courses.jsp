<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>Course Tracker App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Course Tracker</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
		
			<!-- put in a add course button -->
			<input type="button" value="Add Course"
					onclick="window.location.href='add-course-form.jsp'"
					class="add-course-button"
			/>
			
			<table>
				<tr>
					<th>Name</th>
					<th>Term</th>
					<th>Credit</th>
					<th>Grade</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempCourse" items="${COURSE_LIST }">
					<!-- set up a link for each course -->
					<c:url var="tempLink" value="CourseControllerServlet">
						<c:param name="command" value="LOAD"/>
						<c:param name="courseId" value="${tempCourse.id }"/>				
					</c:url>
					
					<!-- set up a link to delete a course -->
					<c:url var="deleteLink" value="CourseControllerServlet">
						<c:param name="command" value="DELETE"/>
						<c:param name="courseId" value="${tempCourse.id }"/>
					</c:url>
					
					<tr>
						<td> ${tempCourse.name }</td>
						<td> ${tempCourse.term }</td>
						<td> ${tempCourse.credit }</td>
						<td> ${tempCourse.grade }</td>
						<td> <a href="${tempLink }">Update</a>
							  |
							 <a href="${deleteLink }"
							 onclick="if (!(confirm('Are you sure you want to delete this course?'))) return false">
							 Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>

</html>