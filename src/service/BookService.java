package service;

import model.Book;
import repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private final BookRepository bookRepository = new BookRepository();

    public List<String> getAuthorTilesWithReadingLevel(Integer authorId) {
        List<Book> books = bookRepository.findByAuthorId(authorId);
        List<String> titlesWithReadingLevel = new ArrayList<>();

        books.stream()
                .map(book -> book.getTitle() + " - " + book.getReadingLevel())
                .collect(Collectors.toCollection(() -> titlesWithReadingLevel));

        return titlesWithReadingLevel;
    }
}
