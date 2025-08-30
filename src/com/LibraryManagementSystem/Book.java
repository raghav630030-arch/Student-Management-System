package com.LibraryManagementSystem;

import java.io.Serializable;

public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String author;
	private int year;
	private int totalCopies;
	private int availableCopies;

	public Book(int id, String title, String author, int year, int totalCopies) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.totalCopies = totalCopies;
		this.availableCopies = totalCopies;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}

	public int getTotalCopies() {
		return totalCopies;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
		if (availableCopies > totalCopies) {
			availableCopies = totalCopies;
		}
	}

	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}

	public void decrementAvailable() {
		if (availableCopies > 0)
			availableCopies--;
	}

	public void incrementAvailable() {
		if (availableCopies < totalCopies)
			availableCopies++;
	}

	@Override
	public String toString() {
		return "Book{id=" + id + ", title='" + title + '\'' + ", author='" + author + '\'' + ", year=" + year
				+ ", total=" + totalCopies + ", available=" + availableCopies + '}';
	}
}