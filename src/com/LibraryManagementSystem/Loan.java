package com.LibraryManagementSystem;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int loanId;
	private int bookId;
	private String borrowerName;
	private LocalDate issueDate;
	private LocalDate dueDate;
	private boolean returned;
	private LocalDate returnDate;

	public Loan(int loanId, int bookId, String borrowerName, LocalDate issueDate, LocalDate dueDate) {
		this.loanId = loanId;
		this.bookId = bookId;
		this.borrowerName = borrowerName;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.returned = false;
		this.returnDate = null;
	}

	public int getLoanId() {
		return loanId;
	}

	public int getBookId() {
		return bookId;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public boolean isReturned() {
		return returned;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void markReturned(LocalDate date) {
		this.returned = true;
		this.returnDate = date;
	}

	@Override
	public String toString() {
		return "Loan{loanId=" + loanId + ", bookId=" + bookId + ", borrower='" + borrowerName + '\'' + ", issueDate="
				+ issueDate + ", dueDate=" + dueDate + (returned ? ", returnedOn=" + returnDate : ", returned=false")
				+ '}';
	}
}