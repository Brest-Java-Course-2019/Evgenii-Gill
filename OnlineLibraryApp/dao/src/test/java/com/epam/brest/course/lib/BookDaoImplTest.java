package com.epam.brest.course.lib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:dao.xml", "classpath:dao-test.xml", "classpath:test-db.xml"})
@Rollback
@Transactional
class BookDaoImplTest {

    private static final int BOOK_ID = 2;
    private static final int FULL_BOOK_LIST = 12;
    private static final String BOOK_TITLE = "Harry Potter and the Chamber of secrets";
    private static final Date ADD_RELEASE_DATE = Date.valueOf("1998-06-02");
    private static final String NEW_BOOK_TITLE = "Alchemist";
    private static final String NEW_RELEASE_DATE = "1988-04-01";
    private static final String EXPECTED_DATE = "1988-04-12";

    @Autowired
    BookDao bookDao;

    @Test
    void findAllBooks() {
        Stream<Book> books = bookDao.findAll();
        assertNotNull(books);
        assertTrue(books.count() > 0);
    }

    @Test
    public void findListCheckCount() {
        Stream<Book> bookStream = bookDao.findAll();
        assertNotNull(bookStream);
        assertEquals(FULL_BOOK_LIST, bookStream.count());
    }

    @Test
    void findById() {
        Book book = bookDao.findById(2).get();
        assertNotNull(book);
        assertEquals(BOOK_ID, book.getBookId().intValue());
        assertEquals(BOOK_TITLE, book.getBookTitle());
        assertEquals(ADD_RELEASE_DATE, book.getReleaseDate());
    }

    @Test
    void getAllBooksWrittenByAuthor() {
        List<Book> bookList = (List<Book>) bookDao.getAllBooksWrittenByAuthor(4);
        assertEquals(1,bookList.size());
    }

    @Test
    void addNewBook() {
        Stream<Book> bookBeforeInsert = bookDao.findAll();

        Book book = new Book();
        book.setBookTitle(NEW_BOOK_TITLE);
        book.setReleaseDate(Date.valueOf(NEW_RELEASE_DATE));

        Book newBook = bookDao.addBook(book).get();
        assertNotNull(newBook.getBookId());

        Stream<Book> bookAfterInsert = bookDao.findAll();
        assertEquals(1, bookAfterInsert.count() - bookBeforeInsert.count());
    }

    @Test
    public void updateBook() {
        Book book = new Book();
        book.setBookTitle(NEW_BOOK_TITLE);
        book.setReleaseDate(Date.valueOf(NEW_RELEASE_DATE));

        Book newBook = bookDao.addBook(book).get();
        assertNotNull(newBook.getBookId());

        book.setBookTitle(NEW_BOOK_TITLE + "2");
        book.setReleaseDate(Date.valueOf(EXPECTED_DATE));
        bookDao.updateBook(book);

        Book updatedBook = bookDao.findById(book.getBookId()).get();

        assertEquals(NEW_BOOK_TITLE + "2", updatedBook.getBookTitle());
        assertEquals(Date.valueOf(EXPECTED_DATE), updatedBook.getReleaseDate());
    }

    @Test
    public void deleteBook() {
        Book book = new Book();
        book.setBookTitle(NEW_BOOK_TITLE);
        book.setReleaseDate(Date.valueOf(EXPECTED_DATE));

        Book newBook = bookDao.addBook(book).get();
        assertNotNull(newBook.getBookId());

        bookDao.deleteBook(newBook.getBookId());

        Assertions.assertThrows(DataAccessException.class, () -> bookDao.findById(book.getBookId()));
    }
}