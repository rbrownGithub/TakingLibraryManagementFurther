package org.example;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String libraryCardNumber;
    private List<Book> booksOnLoan;
    private double lateFees;

    public User(String name, String libraryCardNumber) {
        this.name = name;
        this.libraryCardNumber = libraryCardNumber;
        this.booksOnLoan = new ArrayList<>();
        this.lateFees = 0.0;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getLibraryCardNumber() { return libraryCardNumber; }
    public List<Book> getBooksOnLoan() { return booksOnLoan; }
    public double getLateFees() { return lateFees; }
    public void setLateFees(double lateFees) { this.lateFees = lateFees; }

    public void borrowBook(Book book) {
        booksOnLoan.add(book);
    }

    public void returnBook(Book book) {
        booksOnLoan.remove(book);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", libraryCardNumber='" + libraryCardNumber + '\'' +
                ", booksOnLoan=" + booksOnLoan +
                ", lateFees=" + lateFees +
                '}';
    }
}