package com.StudentManagementSystem;

import java.util.Scanner;

public class StudentManagementApp {
	public static void main(String[] args) {
		StudentService service = new StudentService();
		service.loadStudents();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("\n=== Student Management System ===");
			System.out.println("1. Add Student");
			System.out.println("2. View All Students");
			System.out.println("3. Search Student by ID");
			System.out.println("4. Update Student");
			System.out.println("5. Delete Student");
			System.out.println("6. Exit");
			System.out.print("Enter choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.print("Enter ID: ");
				int id = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter Name: ");
				String name = sc.nextLine();
				System.out.print("Enter Age: ");
				int age = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter Course: ");
				String course = sc.nextLine();
				service.addStudent(new Student(id, name, age, course));
				System.out.println("Student added.");
				break;

			case 2:
				service.viewStudents();
				break;

			case 3:
				System.out.print("Enter Student ID to search: ");
				int sid = sc.nextInt();
				Student s = service.searchStudent(sid);
				System.out.println((s != null) ? s : "Student not found.");
				break;

			case 4:
				System.out.print("Enter ID to update: ");
				int uid = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter new Name: ");
				String newName = sc.nextLine();
				System.out.print("Enter new Age: ");
				int newAge = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter new Course: ");
				String newCourse = sc.nextLine();
				service.updateStudent(uid, newName, newAge, newCourse);
				break;

			case 5:
				System.out.print("Enter ID to delete: ");
				int did = sc.nextInt();
				service.deleteStudent(did);
				break;

			case 6:
				System.out.println("Exiting... Goodbye!");
				sc.close();
				System.exit(0);

			default:
				System.out.println("Invalid choice.");
			}
		}
	}
}