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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class AuthorDaoImpl implements AuthorDao {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorDaoImpl.class);

    private final static String AUTHOR_ID = "authorId";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "lastName";

    /**
     * NamedParameterJdbcTemplate.
     */
    @Autowired
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${selectAllAuthors}")
    private String selectAllAuthorsSQL;

    @Value("${selectAuthorById}")
    private String selectAuthorByIdSQL;

    @Value("${selectAuthorByFirstAndLastName}")
    private String selectAuthorByFirstAndLastNameSQL;

    @Value("${insertAuthor}")
    private String insertAuthorSQL;

    @Value("${updateAuthor}")
    private String updateAuthorSQL;

    @Value("${deleteAuthor}")
    private String deleteAuthorSQL;

    public AuthorDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Author> findAll() {
        LOGGER.debug("findAll({})");
        List<Author> authorList = namedParameterJdbcTemplate
                .query(selectAllAuthorsSQL, BeanPropertyRowMapper.newInstance(Author.class));
        return authorList.stream();
    }

    @Override
    public Optional<Author> findById(Integer authorId) {
        LOGGER.debug("findById({})", authorId);
        Author author = namedParameterJdbcTemplate.queryForObject(
                selectAuthorByIdSQL,
                new MapSqlParameterSource(AUTHOR_ID, authorId),
                BeanPropertyRowMapper.newInstance(Author.class));
        return Optional.ofNullable(author);
    }

    @Override
    public Author selectAuthorByFirstAndLastName(String firstName, String lastName) {
        LOGGER.debug("selectAuthorByFirstAndLastName({} {})", firstName, lastName);
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put(FIRST_NAME, firstName);
        stringObjectMap.put(LAST_NAME, lastName);
        return namedParameterJdbcTemplate.queryForObject(selectAuthorByFirstAndLastNameSQL, stringObjectMap,
                BeanPropertyRowMapper.newInstance(Author.class));
    }

    @Override
    public Optional<Author> addAuthor(Author author) {
        LOGGER.debug("addAuthor({} {})", author.getFirstName(), author.getLastName());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(AUTHOR_ID, author.getAuthorId());
        parameterSource.addValue(FIRST_NAME, author.getFirstName());
        parameterSource.addValue(LAST_NAME, author.getLastName());
        namedParameterJdbcTemplate.update(insertAuthorSQL, parameterSource, keyHolder);
        return Optional.of(author);
    }

    @Override
    public void updateAuthor(Author author) {
        LOGGER.debug("updateAuthor({})", author);
        Optional.of(namedParameterJdbcTemplate.update(updateAuthorSQL, new BeanPropertySqlParameterSource(author)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update author in DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated == 1;
    }

    @Override
    public void deleteAuthor(int authorId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(AUTHOR_ID, authorId);
        Optional.of(namedParameterJdbcTemplate.update(deleteAuthorSQL, parameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete author from DB"));
    }
}


