package repository;

import model.Book;
import model.LexileReadingLevel;
import model.ReadingLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepository {
    private final List<Book> books = new ArrayList<>();

    public BookRepository() {
        ReadingLevel rl1 = new LexileReadingLevel(1, "First grade", "200");
        ReadingLevel rl2 = new LexileReadingLevel(2, "Second grade", "18");

        books.add(new Book(1, 1, rl1, "Arrays in the Sun"));
        books.add(new Book(2, 2, null, "Binary New World"));
        books.add(new Book(3, 3, null, "Bit and The Endian"));
        books.add(new Book(4, 1, null, "Catch 10110"));
        books.add(new Book(5, 1, rl2, "Charlotte's Website"));
        books.add(new Book(6, 3, rl1, "One Fish Two Fish"));
    }

    public List<Book> findByAuthorId(Integer authorId) {
        return books.stream()
                .filter(book -> book.getAuthorId().equals(authorId))
                .collect(Collectors.toList());
    }
}
