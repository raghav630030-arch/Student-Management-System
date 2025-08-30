package com.Practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

	private final static String FILE_NAME = "C:\\Users\\jvr11\\Desktop\\My Projects\\Student Management System\\prac.txt";
	List<Student> student = new ArrayList<Student>();

	// Loading from the file to object
	@SuppressWarnings({ "unchecked" })
	public void loadStudent() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			student = (List<Student>) ois.readObject();
		} catch (FileNotFoundException e) {
			// Ignore First Time
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// save the object to file
	public void saveStudent(Student s) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			oos.writeObject(s);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// adding student to the file
	public void addStudent(Student s) {
		student.add(s);
		saveStudent(s);
	}

	// seeing the student from file
	public void viewStudent() {
		if (student.isEmpty()) {
			System.out.println("No Student Found");
		} else {
			student.forEach(System.out::println);
		}
	}

	// searching the student
	public Student searchStudent(int id) {
		return student.stream().filter(st -> st.getId() == id).findFirst().orElse(null);
	}

	// update the student
	public void updateStudent(int id, String newName, int newAge, String newDept) {
		Student s = searchStudent(id);
		if (s == null) {
			System.out.println("Student is not Found");
		} else {
			s.setId(id);
			s.setName(newName);
			s.setAge(newAge);
			s.setDept(newDept);
			System.out.println("Student updated successfully...");
		}
	}

	// delete the student
	public void deleteStudent(int id) {
		Student s = searchStudent(id);
		if (s == null) {
			System.out.println("No Student Found");
		} else {
			student.remove(s);
			System.out.println("Student Removed Successfully...");
		}
	}
  
}
