package com.epam.brest.course.lib;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The {@code Book} - this is one of the entities in project.
 * Have some simple methods.
 *
 * @author Evgenii Gill
 */
public class Book {
    /**
     * The value is used for identification in data base.
     */
    private Integer bookId;
    /**
     * This is book title.
     */
    private String bookTitle;
    /**
     * This is book release date.
     */
    private Date releaseDate;
    /**
     * The value is used for identification in data base
     * author of this book.
     */
    private Integer bookAuthorId;

    /**
     * The value is used to format date to "yyyy-MM-dd".
     */
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Initializes a newly created {@code Book} object.
     */
    public Book() {

    }

    /**
     * Initializes a newly created {@code Book} object
     * and the appropriate fields are set.
     *
     * @param bookId       is book ID.
     * @param bookTitle    is book title.
     * @param releaseDate  is book release date.
     * @param bookAuthorId is author's ID of this book.
     *                     A {@code Book}
     */
    public Book(Integer bookId, String bookTitle, Date releaseDate, Integer bookAuthorId) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.releaseDate = releaseDate;
        this.bookAuthorId = bookAuthorId;
    }

    /**
     * @return Returns the book ID.
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * Sets the book ID to the <code>bookId</code>.
     *
     * @param bookId the book ID.
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * @return Returns the book title.
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     * Sets the book title to the <code>bookTitle</code>.
     *
     * @param bookTitle the book title.
     */
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /**
     * @return Returns the book release date.
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets the book release date to the <code>releaseDate</code>.
     *
     * @param releaseDate the book release date.
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return Returns the author's id of this book.
     */
    public Integer getBookAuthorId() {
        return bookAuthorId;
    }

    /**
     * Sets the author's id of this book to the <code>bookAuthorId</code>.
     *
     * @param bookAuthorId the author's id of this book.
     */
    public void setBookAuthorId(Integer bookAuthorId) {
        this.bookAuthorId = bookAuthorId;
    }

    /**
     * @return Returns the book release date in format "yyyy-MM-dd".
     */
    public static SimpleDateFormat getFORMATTER() {
        return FORMATTER;
    }

    /**
     * @return a string representation of the book.
     */
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", releaseDate=" + releaseDate +
                ", bookAuthorId=" + bookAuthorId +
                '}';
    }
}
