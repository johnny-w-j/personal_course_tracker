<!DOCTYPE html>

<html>

<head>
	<title>Update Course</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-course-style.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Course Tracker</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Course</h3>
		<form action="CourseControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="courseId" value="${THE_COURSE.id }" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="name" 
									value="${THE_COURSE.name }"/></td>
					</tr>
					
					<tr>
						<td><label>Term:</label></td>
						<td><input type="text" name="term"
									value="${THE_COURSE.term }"/></td>
					</tr>
					
					<tr>
						<td><label>Credit:</label></td>
						<td><input type="text" name="credit"
									value="${THE_COURSE.credit }"/></td>
					</tr>
					
					<tr>
						<td><label>Grade:</label></td>
						<td><input type="text" name="grade"
									value="${THE_COURSE.grade }"/></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save"/></td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div style="clear:both;"></div>
		<p>
			<a href="CourseControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>