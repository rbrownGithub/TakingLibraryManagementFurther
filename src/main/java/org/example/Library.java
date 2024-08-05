package org.example;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String title) {
        books.removeIf(book -> book.getTitle().equals(title));
    }

    public List<Book> findBooksByYear(int year) {
        return books.stream()
                .filter(book -> book.getPublicationYear() == year)
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public Optional<Book> findBookWithMostPages() {
        return books.stream()
                .max(Comparator.comparingInt(Book::getPages));
    }

    public List<Book> findBooksWithMoreThanPages(int pages) {
        return books.stream()
                .filter(book -> book.getPages() > pages)
                .collect(Collectors.toList());
    }

    public List<String> getAllBookTitlesSorted() {
        return books.stream()
                .map(Book::getTitle)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByCategory(String category) {
        return books.stream()
                .filter(book -> book.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public void loanBookToUser(Book book, User user) {
        if (!book.isOnLoan()) {
            book.setOnLoan(true);
            user.borrowBook(book);
        } else {
            System.out.println("Book is already on loan.");
        }
    }

    public void returnBook(Book book, User user) {
        if (book.isOnLoan()) {
            book.setOnLoan(false);
            user.returnBook(book);
            calculateLateFees(book, user);
        } else {
            System.out.println("This book was not on loan.");
        }
    }

    private void calculateLateFees(Book book, User user) {
        LocalDate dueDate = book.getDueDate();
        if (dueDate != null) {
            LocalDate today = LocalDate.now();
            if (today.isAfter(dueDate)) {
                long daysLate = today.toEpochDay() - dueDate.toEpochDay();
                double lateFee = daysLate * 0.50; // 50 cents per day
                user.setLateFees(user.getLateFees() + lateFee);
            }
        }
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public void updateLateFees() {
        users.forEach(user -> {
            user.getBooksOnLoan().forEach(book -> {
                calculateLateFees(book, user);
            });
        });
    }

    // Example of using Predicate
    public List<Book> findBooks(Predicate<Book> condition) {
        return books.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    // Example of using Consumer
    public void performOnAllBooks(java.util.function.Consumer<Book> action) {
        books.forEach(action);
    }

    // Example of using Supplier
    public Book getRandomBook(java.util.function.Supplier<Integer> randomIndex) {
        int index = randomIndex.get() % books.size();
        return books.get(index);
    }

    // Example of using Function
    public <R> List<R> mapBooks(java.util.function.Function<Book, R> mapper) {
        return books.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}