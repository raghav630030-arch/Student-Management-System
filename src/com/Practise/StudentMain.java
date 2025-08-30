package com.Practise;

import java.util.Scanner;

public class StudentMain {
	public static void main(String[] args) {
		StudentService service = new StudentService();
		service.loadStudent();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("\n---Student Management System---");
			System.out.println("1. Add Student");
			System.out.println("2. View Students");
			System.out.println("3. Search Student");
			System.out.println("4. Update Student");
			System.out.println("5. Delete Student");
			System.out.println("6. Exit");
			System.out.print("Enter choice: ");

			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter Id");
				int id = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter name");
				String name = sc.nextLine();
				System.out.println("Enter age");
				int age = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter dept");
				String dept = sc.next();
				service.addStudent(new Student(id, name, age, dept));
				System.out.println("Add student Successfully...");
				break;

			case 2:
				service.viewStudent();
				break;

			case 3:

				System.out.println("Enter the id to saerch");
				int sid = sc.nextInt();
				Student search = service.searchStudent(sid);
				System.out.println((search != null) ? search : "Student not Found ");
				break;

			case 4:
				System.out.println("Enter the datails to update student");
				int nId = sc.nextInt();
				sc.nextLine();
				String nName = sc.nextLine();
				int nAge = sc.nextInt();
				sc.nextLine();
				String nDept = sc.next();
				service.updateStudent(nId, nName, nAge, nDept);
				System.out.println("Add student Successfully...");
				break;

			case 5:
				System.out.println("Enter the id to delete a student");
				int dId = sc.nextInt();
				service.deleteStudent(dId);
				break;

			case 6:
				System.out.println("Bye Bye...");
				sc.close();
				System.exit(0);

			default:
				System.out.println("Invalid case...");
			}
		}
	}
}
