package com.epam.brest.course.lib;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Book DAO - is an interface that provides access to a database.
 */

public interface BookDao {

    /**
     * Find all books.
     *
     * @return all books
     */
    Stream<Book> findAll();

    /**
     * Find book by ID.
     *
     * @param bookId is book's ID.
     * @return book.
     */
    Optional<Book> findById(Integer bookId);

    /**
     * Get book by title and release date.
     *
     * @param bookTitle   is book title.
     * @param releaseDate is book release date.
     * @return book.
     */
    Book getBookByTitleAndReleaseDate(String bookTitle, Date releaseDate);

    /**
     * Get all book list written by this author.
     *
     * @return all book list created by this author.
     */
    Book getAllBooksWrittenByAuthor(Integer authorId);

    /**
     * Adds the book to the database and returns
     * the ID that the database assigned to him.
     *
     * @param book book.
     * @return book's ID.
     */
    Optional<Book> addBook(Book book);

    /**
     * Updates the book in the database and returns
     * the number of rows affected in data base.
     *
     * @param book book.
     */
    void updateBook(Book book);

    /**
     * Deletes the book in the database and returns
     * the number of rows affected in data base.
     *
     * @param bookId is book's ID.
     */
    void deleteAuthor(Book bookId);
}
