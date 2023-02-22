package model;

import java.util.Optional;

public class Book {
    private Integer id;
    private Integer authorId;
    private  ReadingLevel readingLevel;
    private String title;

    public Book(Integer id, Integer authorId, ReadingLevel readingLevel, String title) {
        this.id = id;
        this.authorId = authorId;
        this.readingLevel = readingLevel;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Optional<ReadingLevel> getReadingLevel() {
        return Optional.ofNullable(readingLevel);
    }

    public void setReadingLevel(ReadingLevel readingLevel) {
        this.readingLevel = readingLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("Book{id=%d, authorId=%d, readingLevel=%s, title='%s'}", id, authorId, readingLevel, title);
    }
}
