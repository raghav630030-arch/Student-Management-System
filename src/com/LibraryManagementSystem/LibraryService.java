package com.LibraryManagementSystem;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {
    private static final String BOOKS_FILE = "C:\\Users\\jvr11\\Desktop\\My Projects\\Library Management System\\books.txt";
    private static final String LOANS_FILE = "C:\\Users\\jvr11\\Desktop\\My Projects\\Library Management System\\loans.txt";
    private static final int DEFAULT_LOAN_DAYS = 14;
    private static final int FINE_PER_DAY = 5; // change as you like

    private List<Book> books = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();
    private int nextLoanId = 1;

    // ---------- Persistence ----------
    @SuppressWarnings("unchecked")
    public void load() {
        // books
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKS_FILE))) {
            books = (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            // first run - ignore
        } catch (Exception e) {
            System.out.println("Error loading books: " + e.getMessage());
        }

        // loans
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(LOANS_FILE))) {
            loans = (List<Loan>) ois.readObject();
            nextLoanId = loans.stream().mapToInt(Loan::getLoanId).max().orElse(0) + 1;
        } catch (FileNotFoundException e) {
            // first run - ignore
        } catch (Exception e) {
            System.out.println("Error loading loans: " + e.getMessage());
        }
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
            oos.writeObject(books);
        } catch (Exception e) {
            System.out.println("Error saving books: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LOANS_FILE))) {
            oos.writeObject(loans);
        } catch (Exception e) {
            System.out.println("Error saving loans: " + e.getMessage());
        }
    }

    // ---------- Books ----------
    public void addBook(Book b) {
        if (findBookById(b.getId()) != null) {
            System.out.println("Book with this ID already exists.");
            return;
        }
        books.add(b);
        save();
        System.out.println("Book added.");
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        books.forEach(System.out::println);
    }

    public Book findBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    public List<Book> searchBooksByTitle(String keyword) {
        String k = keyword.toLowerCase();
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(k))
                .collect(Collectors.toList());
    }

    public void updateBook(int id, String newTitle, String newAuthor, int newYear, int newTotalCopies) {
        Book b = findBookById(id);
        if (b == null) {
            System.out.println("Book not found.");
            return;
        }
        b.setTitle(newTitle);
        b.setAuthor(newAuthor);
        b.setYear(newYear);
        int delta = newTotalCopies - b.getTotalCopies();
        b.setTotalCopies(newTotalCopies);
        // Adjust available if increasing copies
        if (delta > 0) b.setAvailableCopies(b.getAvailableCopies() + delta);
        save();
        System.out.println("Book updated.");
    }

    public void deleteBook(int id) {
        Book b = findBookById(id);
        if (b == null) {
            System.out.println("Book not found.");
            return;
        }
        // Do not allow deleting if copies are currently loaned out
        int copiesOut = b.getTotalCopies() - b.getAvailableCopies();
        if (copiesOut > 0) {
            System.out.println("Cannot delete: " + copiesOut + " copies are currently on loan.");
            return;
        }
        books.remove(b);
        save();
        System.out.println("Book deleted.");
    }

    // ---------- Loans ----------
    public void issueBook(int bookId, String borrowerName) {
        Book b = findBookById(bookId);
        if (b == null) {
            System.out.println("Book not found.");
            return;
        }
        if (b.getAvailableCopies() <= 0) {
            System.out.println("No available copies.");
            return;
        }
        LocalDate issue = LocalDate.now();
        LocalDate due = issue.plusDays(DEFAULT_LOAN_DAYS);
        Loan loan = new Loan(nextLoanId++, bookId, borrowerName, issue, due);
        loans.add(loan);
        b.decrementAvailable();
        save();
        System.out.println("Issued. Loan ID: " + loan.getLoanId() + ", Due: " + due);
    }

    public void returnBook(int loanId) {
        Loan ln = loans.stream().filter(l -> l.getLoanId() == loanId && !l.isReturned()).findFirst().orElse(null);
        if (ln == null) {
            System.out.println("Active loan not found with ID: " + loanId);
            return;
        }
        Book b = findBookById(ln.getBookId());
        if (b == null) {
            System.out.println("Book record missing for loan. Returning anyway.");
        }
        LocalDate today = LocalDate.now();
        ln.markReturned(today);
        if (b != null) b.incrementAvailable();
        save();

        long overdueDays = Math.max(0, ChronoUnit.DAYS.between(ln.getDueDate(), today));
        int fine = (int) overdueDays * FINE_PER_DAY;
        if (overdueDays > 0) {
            System.out.println("Returned. Overdue by " + overdueDays + " day(s). Fine = " + fine);
        } else {
            System.out.println("Returned on time. No fine.");
        }
    }

    public void listActiveLoans() {
        List<Loan> active = loans.stream().filter(l -> !l.isReturned()).collect(Collectors.toList());
        if (active.isEmpty()) {
            System.out.println("No active loans.");
            return;
        }
        active.forEach(System.out::println);
    }

    public void listAllLoans() {
        if (loans.isEmpty()) {
            System.out.println("No loans found.");
            return;
        }
        loans.forEach(System.out::println);
    }
}