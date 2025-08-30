package com.StudentManagementSystem;

import java.io.Serializable;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int age;
	private String course;

	public Student(int id, String name, int age, String course) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Course: " + course;
	}
}