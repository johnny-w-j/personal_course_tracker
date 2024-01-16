package com.jwj11.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class CourseControllerServlet
 */
@WebServlet("/CourseControllerServlet")
public class CourseControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CourseDbUtil courseDbUtil;

	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		// create our course db util... and pass in the connpool/datasource
		try {
			courseDbUtil = new CourseDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// route to appropriate method
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			switch (theCommand) {
				case "LIST":
					listCourses(request, response);
					break;
				case "ADD":
					addCourse(request, response);
					break;
				case "LOAD":
					loadCourse(request, response);
					break;
				case "UPDATE":
					updateCourse(request,response);
					break;
				case "DELETE":
					deleteCourse(request, response);
					break;
					
				default:
					listCourses(request, response);
			}
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read course id from form data
		String theCourseId = request.getParameter("courseId");
		
		// delete course from database
		courseDbUtil.deleteCourse(theCourseId);
		
		// send them back to "list courses" page
		listCourses(request, response);
		
	}

	private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read course info from form data
		int id = Integer.parseInt(request.getParameter("courseId"));
		String name = request.getParameter("name");
		String term = request.getParameter("term");
		String credit = request.getParameter("credit");
		String grade = request.getParameter("grade");
		
		// create a new course object
		Course theCourse = new Course(id, name, term, credit, grade);
		
		// perform update on database
		courseDbUtil.updateCourse(theCourse);
		
		// send them back to the "list courses" page
		listCourses(request, response);
	}

	private void loadCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read course id from form data
		String theCourseId = request.getParameter("courseId");
		
		// get course from database (db util)
		Course theCourse = courseDbUtil.getCourse(theCourseId);
		
		// place course in the request attribute
		request.setAttribute("THE_COURSE", theCourse);
		
		// send to jsp page: update-course-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-course-form.jsp");
		dispatcher.forward(request, response);
	}

	private void addCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read course info from form data
		String name = request.getParameter("name");
		String term = request.getParameter("term");
		String credit = request.getParameter("credit");
		String grade = request.getParameter("grade");
		
		// create a new course object
		Course theCourse = new Course(name, term, credit, grade);
		
		// add the course to the database
		courseDbUtil.addCourse(theCourse);
		
		// send back to main page (the course list)
		listCourses(request, response);
	}

	private void listCourses(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		
		// courses from db util
		List<Course> courses = courseDbUtil.getCourses();
		
		// add courses to the request
		request.setAttribute("COURSE_LIST", courses);
		
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-courses.jsp");
		dispatcher.forward(request, response);
	}
}