package com.epam.brest.course.web_app.rest;

import com.epam.brest.course.dao.Book;
import com.epam.brest.course.dao.BookDaoImpl;
import com.epam.brest.course.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service("bookRest")
public class BookRestClient implements BookService {

    @Value("${book.RestClientUrl}")
    private String url;

    @Autowired
    RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookDaoImpl.class);

    @Override
    public Stream<Book> findAll() {
        LOGGER.debug("findAllBooks()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, Stream.class);
        Stream<Book> books = (Stream<Book>) responseEntity.getBody();
        return books;
    }

    @Override
    public final Book findById(final Integer bookId) {
        LOGGER.debug("findById");
        ResponseEntity<Book> responseEntity =
                restTemplate.getForEntity(url + "/" + bookId, Book.class);
        Book book = responseEntity.getBody();
        return book;
    }

    @Override
    public Optional<Book> addBook(final Book book) {
        LOGGER.debug("addBook({})", book);
        ResponseEntity<Book> responseEntity =
                restTemplate.postForEntity(url, book, Book.class);
        Book resultBook = responseEntity.getBody();
        return Optional.ofNullable(resultBook);
    }

    @Override
    public void updateBook(final Book book) {
        LOGGER.debug("updateBook", book);
        restTemplate.put(url, book);
    }

    @Override
    public void deleteBook(final Integer bookId) {
        LOGGER.debug("deleteBook", bookId);
        restTemplate.delete(url + "/" + bookId);
    }
}
