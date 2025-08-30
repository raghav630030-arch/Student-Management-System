package com.LibraryManagementSystem;

import java.util.List;
import java.util.Scanner;

public class LibraryApp {
	public static void main(String[] args) {
		LibraryService service = new LibraryService();
		service.load();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("\n=== Library Management System ===");
			System.out.println("1. Add Book");
			System.out.println("2. List All Books");
			System.out.println("3. Search Books by Title");
			System.out.println("4. Update Book");
			System.out.println("5. Delete Book");
			System.out.println("6. Issue Book");
			System.out.println("7. Return Book");
			System.out.println("8. List Active Loans");
			System.out.println("9. List All Loans");
			System.out.println("10. Exit");
			System.out.print("Enter choice: ");

			int choice = safeInt(sc);

			switch (choice) {
			case 1:
				System.out.print("Enter Book ID: ");
				int id = safeInt(sc);
				sc.nextLine();
				System.out.print("Enter Title: ");
				String title = sc.nextLine();
				System.out.print("Enter Author: ");
				String author = sc.nextLine();
				System.out.print("Enter Year: ");
				int year = safeInt(sc);
				System.out.print("Enter Total Copies: ");
				int copies = safeInt(sc);
				service.addBook(new Book(id, title, author, year, copies));
				break;

			case 2:
				service.listBooks();
				break;

			case 3:
				sc.nextLine();
				System.out.print("Enter keyword in title: ");
				String key = sc.nextLine();
				List<Book> results = service.searchBooksByTitle(key);
				if (results.isEmpty())
					System.out.println("No matches.");
				else
					results.forEach(System.out::println);
				break;

			case 4:
				System.out.print("Enter Book ID to update: ");
				int uid = safeInt(sc);
				sc.nextLine();
				System.out.print("New Title: ");
				String nt = sc.nextLine();
				System.out.print("New Author: ");
				String na = sc.nextLine();
				System.out.print("New Year: ");
				int ny = safeInt(sc);
				System.out.print("New Total Copies: ");
				int nc = safeInt(sc);
				service.updateBook(uid, nt, na, ny, nc);
				break;

			case 5:
				System.out.print("Enter Book ID to delete: ");
				int did = safeInt(sc);
				service.deleteBook(did);
				break;

			case 6:
				System.out.print("Enter Book ID to issue: ");
				int bid = safeInt(sc);
				sc.nextLine();
				System.out.print("Borrower Name: ");
				String borrower = sc.nextLine();
				service.issueBook(bid, borrower);
				break;

			case 7:
				System.out.print("Enter Loan ID to return: ");
				int lid = safeInt(sc);
				service.returnBook(lid);
				break;

			case 8:
				service.listActiveLoans();
				break;

			case 9:
				service.listAllLoans();
				break;

			case 10:
				System.out.println("Exiting... Goodbye!");
				sc.close();
				System.exit(0);
				break;

			default:
				System.out.println("Invalid choice.");
			}
		}
	}

	private static int safeInt(Scanner sc) {
		while (!sc.hasNextInt()) {
			System.out.print("Please enter a valid number: ");
			sc.next(); // discard invalid token
		}
		return sc.nextInt();
	}
}