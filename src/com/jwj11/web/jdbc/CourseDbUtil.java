package com.jwj11.web.jdbc;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

public class CourseDbUtil {
	
	private DataSource dataSource;
	
	public CourseDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Course> getCourses() throws Exception {
		List<Course> courses = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from course order by term";
			
			myStmt = myConn.createStatement();
		
			// execute query
			myRs = myStmt.executeQuery(sql);
		
			// process result set
			while (myRs.next()) {
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String term = myRs.getString("term");
				String credit = myRs.getString("credit");
				String grade = myRs.getString("grade");
				
				// create new course object
				Course tempCourse = new Course(id, name, term, credit, grade);
				
				// add it to the list of courses
				courses.add(tempCourse);
			}
		
			return courses;
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}

	public void addCourse(Course theCourse) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// create sql for insert
			myConn = dataSource.getConnection();
			
			// set the param values for the course
			String sql = "insert into course "
						+ "(name, term, credit, grade) "
						+ "values (?, ?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			
			// execute sql insert
			myStmt.setString(1, theCourse.getName());
			myStmt.setString(2, theCourse.getTerm());
			myStmt.setString(3, theCourse.getCredit());
			myStmt.setString(4, theCourse.getGrade());
			
			// clean up JDBC objects
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Course getCourse(String theCourseId) throws Exception{
		Course theCourse = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int courseId;
		
		try {
			//convert course to int
			courseId = Integer.parseInt(theCourseId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected course
			String sql = "select * from course where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, courseId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");
				String term = myRs.getString("term");
				String credit = myRs.getString("credit");
				String grade = myRs.getString("grade");
				
				// use the courseId during construction
				theCourse = new Course(courseId, name, term, credit, grade);
			}
			else {
				throw new Exception("Could not find course id: " + courseId);
			}
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
		return theCourse;
	}

	public void updateCourse(Course theCourse) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// get db connection
			myConn = dataSource.getConnection();
		
			// create SQL update statement
			String sql = "update course " +
							"set name=?, term=?, credit=?, grade=? " +
							"where id=?";
		
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1,  theCourse.getName());
			myStmt.setString(2,  theCourse.getTerm());
			myStmt.setString(3,  theCourse.getCredit());
			myStmt.setString(4,  theCourse.getGrade());
			myStmt.setInt(5,  theCourse.getId());
		
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteCourse(String theCourseId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// convert course id to int
			int courseId = Integer.parseInt(theCourseId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete course
			String sql = "delete from course where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, courseId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
}
