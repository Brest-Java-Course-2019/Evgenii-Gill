package com.epam.brest.course.dao;

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
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

@Repository
public class BookDaoImpl implements BookDao {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BookDaoImpl.class);

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private static final String BOOK_ID = "bookId";
    private static final String BOOK_TITLE = "bookTitle";
    private static final String RELEASE_DATE = "releaseDate";
    private static final String BOOK_AUTHOR_ID = "bookAuthorId";

    /**
     * NamedParameterJdbcTemplate.
     */
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public BookDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Value("${selectAllBooks}")
    private String selectAllBooksSQL;

    @Value("${updateBook}")
    private String updateBookSQL;

    @Value("${deleteBook}")
    private String deleteBookSQL;

    @Value("${selectBooksWrittenByAuthor}")
    private String selectBooksWrittenByAuthorSQL;

    @Value("${insertBook}")
    private String insertBookSQL;

    @Value("${selectBookByTitleAndReleaseDate}")
    private String selectBookByTitleAndReleaseDateSQL;

    @Value("${selectBookById}")
    private String selectBookByIdSQL;

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
    public List<Book> getAllBooksWrittenByAuthor(Integer authorId) {
        LOGGER.debug("getAllBooksWrittenByAuthor({})", authorId);
        Map<String, Object> map = new HashMap<>();
        map.put(BOOK_AUTHOR_ID, authorId);
        return namedParameterJdbcTemplate.query(selectBooksWrittenByAuthorSQL, map,
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
        book.setBookId(keyHolder.getKey().intValue());
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
