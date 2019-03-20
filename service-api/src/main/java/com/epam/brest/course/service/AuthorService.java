package com.epam.brest.course.service;

import com.epam.brest.course.dao.Author;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Author service interface.
 */

public interface AuthorService {

    /**
     * Find all authors stream.
     *
     * @return authors.
     */

    Stream<Author> findAll();

    /**
     * Find author by Id from database.
     *
     * @param authorId
     * @return author
     */
    Author findById(Integer authorId);

    /**
     * Add author to database.
     *
     * @param author new author
     * @return author with id
     */
    Optional<Author> addAuthor(Author author);

    /**
     * Update author in database.
     *
     * @param author author
     */
    void updateAuthor(Author author);

    /**
     * Remove author by id from database.
     *
     * @param authorId author id
     */
    void deleteAuthor(Integer authorId);

}
