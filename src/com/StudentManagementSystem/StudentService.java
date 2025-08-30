package com.StudentManagementSystem;

import java.io.*;
import java.util.*;

public class StudentService {
	private static final String FILE_NAME = "C:\\Users\\jvr11\\Desktop\\My Projects\\Student Management System\\students.txt";
	private List<Student> students = new ArrayList<>();

	// Load students from file
	@SuppressWarnings("unchecked")
	public void loadStudents() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			students = (List<Student>) ois.readObject();
		} catch (FileNotFoundException e) {
			// first run, ignore
		} catch (Exception e) {
			System.out.println("Error loading students: " + e.getMessage());
		}
	}

	// Save students to file
	public void saveStudents() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			oos.writeObject(students);
		} catch (Exception e) {
			System.out.println("Error saving students: " + e.getMessage());
		}
	}

	// Add student
	public void addStudent(Student s) {
		students.add(s);
		saveStudents();
	}

	// View all students
	public void viewStudents() {
		if (students.isEmpty()) {
			System.out.println("No students found.");
		} else {
			students.forEach(System.out::println);
		}
	}

	// Search student
	public Student searchStudent(int id) {
		return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
	}

	// Update student
	public void updateStudent(int id, String newName, int newAge, String newCourse) {
		Student s = searchStudent(id);
		if (s != null) {
			s.setName(newName);
			s.setAge(newAge);
			s.setCourse(newCourse);
			saveStudents();
			System.out.println("Student updated successfully.");
		} else {
			System.out.println("Student not found.");
		}
	}

	// Delete student
	public void deleteStudent(int id) {
		Student s = searchStudent(id);
		if (s != null) {
			students.remove(s);
			saveStudents();
			System.out.println("Student deleted successfully.");
		} else {
			System.out.println("Student not found.");
		}
	}
}