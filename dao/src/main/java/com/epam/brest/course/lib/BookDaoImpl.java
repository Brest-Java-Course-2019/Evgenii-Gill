package com.epam.brest.course.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class BookDaoImpl implements BookDao {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BookDaoImpl.class);

    /**
     * NamedParameterJdbcTemplate.
     */
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private final static String BOOK_ID = "bookId";
    private final static String BOOK_TITLE = "bookTitle";
    private static final String RELEASE_DATE = "releaseDate";
    private static final String BOOK_AUTHOR_ID = "bookAuthorId";

    public BookDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Value("${selectAllBooks}")
    String selectAllBooksSQL;

    @Value("${updateBook}")
    String updateBookSQL;

    @Value("${deleteBook}")
    String deleteBookSQL;

    @Value("${selectBooksWrittenByAuthor}")
    String selectBooksWrittenByAuthorSQL;

    @Value("${insertBook}")
    String insertBookSQL;

    @Value("${selectBookByTitleAndReleaseDate}")
    String selectBookByTitleAndReleaseDateSQL;

    @Value("${selectBookById}")
    String selectBookByIdSQL;

    @Override
    public Stream<Book> findAll() {
        LOGGER.debug("findAll({})");
        List<Book> bookList = namedParameterJdbcTemplate
                .query(selectAllBooksSQL, BeanPropertyRowMapper.newInstance(Book.class));
        return bookList.stream();
    }

    @Override
    public Optional<Book> findById(Integer bookId) {
        LOGGER.debug("findById({})", bookId);
        Book book = namedParameterJdbcTemplate.queryForObject(
                selectBookByIdSQL,
                new MapSqlParameterSource(BOOK_ID, bookId),
                BeanPropertyRowMapper.newInstance(Book.class));
        return Optional.ofNullable(book);
    }

    @Override
    public Book getBookByTitleAndReleaseDate(String bookTitle, Date releaseDate) {
        LOGGER.debug("getBookByTitleAndReleaseDate({}, {})", bookTitle, FORMATTER.format(releaseDate));
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put(BOOK_TITLE, bookTitle);
        objectMap.put(RELEASE_DATE, FORMATTER.format(releaseDate));
        return namedParameterJdbcTemplate.queryForObject(selectBookByTitleAndReleaseDateSQL, objectMap,
                BeanPropertyRowMapper.newInstance(Book.class));
    }

    @Override
    public Book getAllBooksWrittenByAuthor(Integer authorId) {
        LOGGER.debug("getAllBooksWrittenByAuthor({})", authorId);
        Map<String, Object> map = new HashMap<>();
        map.put(BOOK_AUTHOR_ID, authorId);
        return namedParameterJdbcTemplate.queryForObject(selectBooksWrittenByAuthorSQL, map,
                BeanPropertyRowMapper.newInstance(Book.class));
    }

    @Override
    public Optional<Book> addBook(Book book) {
        LOGGER.debug("addBook({} {})", book.getBookTitle(), book.getReleaseDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(BOOK_ID, book.getBookId());
        parameterSource.addValue(BOOK_TITLE, book.getBookTitle());
        parameterSource.addValue(RELEASE_DATE, book.getReleaseDate());
        parameterSource.addValue(BOOK_AUTHOR_ID, book.getBookAuthorId());
        namedParameterJdbcTemplate.update(insertBookSQL, parameterSource, keyHolder);
        return Optional.of(book);
    }

    @Override
    public void updateBook(Book book) {
        LOGGER.debug("updateBook({})", book);
        Optional.of(namedParameterJdbcTemplate.update(updateBookSQL, new BeanPropertySqlParameterSource(book)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update book in DB"));
    }


    @Override
    public void deleteBook(int bookId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(BOOK_ID, bookId);
        Optional.of(namedParameterJdbcTemplate.update(deleteBookSQL, parameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete book from DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }
}
