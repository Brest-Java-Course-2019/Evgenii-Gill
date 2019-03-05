package com.epam.brest.course.lib;

import java.util.Date;
import java.util.List;
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
     * Get list of all book
     * with a date filter.
     *
     * @param fromDate start date.
     * @param toDate   finish date.
     * @return list of book.
     */
    List<Book> getAllBookWithDateFilter(Date fromDate, Date toDate);

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
