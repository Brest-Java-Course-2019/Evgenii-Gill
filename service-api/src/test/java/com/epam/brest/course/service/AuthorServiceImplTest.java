package com.epam.brest.course.service;

import com.epam.brest.course.dao.Author;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The {@code AuthorServiceImplTest} - is a test class for AuthorServiceImpl.
 *
 * @author Evgenii Gill
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:service-test.xml", "classpath:dao.xml"})
class AuthorServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorServiceImpl.class);


    @Autowired
    private AuthorService authorService;

    @Test
    void findAll() {
        Stream<Author> authors = authorService.findAll();
        assertNotNull(authors);
    }

    @Test
    void findById() {
        // given
        Optional<Author> firstAuthor = authorService.findAll().findFirst();
        assertTrue(firstAuthor.isPresent());
        Integer id = firstAuthor.get().getAuthorId();

        // when
        Author author = authorService.findById(id);
        assertNotNull(author);

        assertEquals(firstAuthor.get().getFirstName(), author.getFirstName());
    }

    @Test
    void addAuthor() {

    }

    @Test
    void updateAuthor() {
    }

    @Test
    void deleteAuthor() {

    }
}