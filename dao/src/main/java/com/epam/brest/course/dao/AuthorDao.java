package com.epam.brest.course.dao;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Author DAO - is an interface that provides access to a database.
 */

public interface AuthorDao {

    /**
     * Find all authors.
     *
     * @return all authors
     */
    Stream<Author> findAll();

    /**
     * Find author by ID.
     *
     * @param authorId is author's ID.
     * @return author.
     */
    Optional<Author> findById(Integer authorId);

    /**
     * Get author by first and last name.
     *
     * @param firstName is author's first name.
     * @param lastName  is author's last name.
     * @return author.
     */
    Author selectAuthorByFirstAndLastName(String firstName, String lastName);

    /**
     * Adds the author to the database and returns
     * the ID that the database assigned to him.
     *
     * @param author author.
     * @return author's ID.
     */
    Optional<Author> addAuthor(Author author);

    /**
     * Updates the author in the database and returns
     * the number of rows affected in data base.
     *
     * @param author author.
     */
    void updateAuthor(Author author);

    /**
     * Deletes the author in the database and returns
     * the number of rows affected in data base.
     *
     * @param authorId is author's ID.
     */
    void deleteAuthor(int authorId);
}
