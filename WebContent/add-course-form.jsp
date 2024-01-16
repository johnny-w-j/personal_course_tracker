<!DOCTYPE html>

<html>

<head>
	<title>Add Course</title>
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
		<h3>Add Course</h3>
		<form action="CourseControllerServlet" method="GET">
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="name" /></td>
					</tr>
					
					<tr>
						<td><label>Term:</label></td>
						<td><input type="text" name="term" /></td>
					</tr>
					
					<tr>
						<td><label>Credit:</label></td>
						<td><input type="text" name="credit" /></td>
					</tr>

					<tr>
						<td><label>Grade:</label></td>
						<td><input type="text" name="grade" /></td>
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