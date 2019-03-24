package com.epam.brest.course.service;

import com.epam.brest.course.dao.Author;
import com.epam.brest.course.dao.AuthorDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * AuthorServiceImpl.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    /**
     * Logger init.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorServiceImpl.class);

    /**
     * Property authorDao.
     */
    @Autowired
    private AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Stream<Author> findAll() {
        LOGGER.debug("Find all authors");
        return authorDao.findAll();
    }

    @Override
    public Author findById(Integer authorId) {
        LOGGER.debug("findById({})", authorId);
        return authorDao.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Failed to get author from DB"));
    }

    @Override
    public Optional<Author> addAuthor(Author author) {
        LOGGER.debug("addAuthor({})", author);
        return authorDao.addAuthor(author);
    }

    @Override
    public void updateAuthor(Author author) {
        LOGGER.debug("update({})", author);
        authorDao.updateAuthor(author);
    }

    @Override
    public void deleteAuthor(Integer authorId) {
        LOGGER.debug("delete({})", authorId);
        authorDao.deleteAuthor(authorId);
    }
}
