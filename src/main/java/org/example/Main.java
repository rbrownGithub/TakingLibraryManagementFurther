package org.example;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books
        library.addBook(new Book("1984", "George Orwell", 1949, 328, "Fiction"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 1960, 281, "Fiction"));
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 180, "Fiction"));
        library.addBook(new Book("Pride and Prejudice", "Jane Austen", 1813, 432, "Fiction"));
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 234, "Fiction"));
        library.addBook(new Book("A Brief History of Time", "Stephen Hawking", 1988, 256, "Science"));

        // Registering users
        User user1 = new User("John Doe", "JD001");
        User user2 = new User("Jane Smith", "JS002");
        library.registerUser(user1);
        library.registerUser(user2);

        // Demonstrating functionality
        System.out.println("Books published in 1951:");
        library.findBooksByYear(1951).forEach(System.out::println);

        System.out.println("\nBooks by Jane Austen:");
        library.findBooksByAuthor("Jane Austen").forEach(System.out::println);

        System.out.println("\nBook with most pages:");
        library.findBookWithMostPages().ifPresent(System.out::println);

        System.out.println("\nBooks with more than 300 pages:");
        library.findBooksWithMoreThanPages(300).forEach(System.out::println);

        System.out.println("\nAll book titles sorted:");
        library.getAllBookTitlesSorted().forEach(System.out::println);

        System.out.println("\nScience books:");
        library.findBooksByCategory("Science").forEach(System.out::println);

        // Loaning and returning books
        Book book1984 = library.findBooks(b -> b.getTitle().equals("1984")).get(0);
        library.loanBookToUser(book1984, user1);
        System.out.println("\nAfter loaning 1984 to John Doe:");
        System.out.println(user1);

// Simulate some time passing
        try {
            Thread.sleep(1000); // Wait for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        library.returnBook(book1984, user1);
        System.out.println("\nAfter returning 1984:");
        System.out.println(user1);

        // Using functional interfaces
        System.out.println("\nBooks with 'The' in the title:");
        library.findBooks(book -> book.getTitle().startsWith("The")).forEach(System.out::println);

        System.out.println("\nPrinting all book titles:");
        library.performOnAllBooks(book -> System.out.println(book.getTitle()));

        System.out.println("\nGetting a random book:");
        Random random = new Random();
        System.out.println(library.getRandomBook(random::nextInt));

        System.out.println("\nMapping books to their titles and authors:");
        List<String> titleAndAuthors = library.mapBooks(book -> book.getTitle() + " by " + book.getAuthor());
        titleAndAuthors.forEach(System.out::println);
    }
}