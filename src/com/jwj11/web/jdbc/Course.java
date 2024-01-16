package com.jwj11.web.jdbc;

public class Course {
	
	private int id;
	private String name;
	private String term;
	private String credit;
	private String grade;
	
	public Course(int id, String name, String term, String credit, String grade) {
		super();
		this.id = id;
		this.name = name;
		this.term = term;
		this.credit = credit;
		this.grade = grade;
	}

	public Course(String name, String term, String credit, String grade) {
		super();
		this.name = name;
		this.term = term;
		this.credit = credit;
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", term=" + term + ", credit=" + credit + ", grade=" + grade
				+ "]";
	}
}