package com.epam.brest.course.service;

import com.epam.brest.course.dao.Book;
import com.epam.brest.course.dao.BookDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.stream.Stream;

public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Stream<Book> findAll() {
        LOGGER.debug("Find all books");
        return bookDao.findAll();
    }

    @Override
    public Book findById(Integer bookId) {
        LOGGER.debug("findById({})", bookId);
        return bookDao.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Failed to get book from DB"));
    }

    @Override
    public Optional<Book> addBook(Book book) {
        LOGGER.debug("addBook({})", book);
        return bookDao.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        LOGGER.debug("updateBook({})", book);
        bookDao.updateBook(book);
    }


    @Override
    public void deleteBook(Integer bookId) {
        LOGGER.debug("deleteBook({})", bookId);
        bookDao.deleteBook(bookId);
    }

}
