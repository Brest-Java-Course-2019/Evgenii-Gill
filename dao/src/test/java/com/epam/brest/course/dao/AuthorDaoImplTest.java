package com.epam.brest.course.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:dao.xml", "classpath:dao-test.xml", "classpath:test-db.xml"})
@Rollback
@Transactional
class AuthorDaoImplTest {

    private static final int FULL_AUTHOR_LIST = 4;
    private static final int AUTHOR_ID = 1;
    private static final String AUTHOR_FIRST_NAME = "Joanne";
    private static final String AUTHOR_LAST_NAME = "Rowling";
    private static final String NEW_AUTHOR_FIRST_NAME = "Arthur";
    private static final String NEW_AUTHOR_LAST_NAME = "Conan Doyle";

    @Autowired
    private AuthorDao authorDao;

    @Test
    void findAllAuthors() {
        Stream<Author> authors = authorDao.findAll();
        assertNotNull(authors);
    }

    @Test
    public void findListCheckCount() {
        Stream<Author> authorStream = authorDao.findAll();
        assertNotNull(authorStream);
        assertEquals(FULL_AUTHOR_LIST, authorStream.count());
    }

    @Test
    public void findAuthorById() {
        Author author = authorDao.findById(1).get();
        assertNotNull(author);
        assertEquals(AUTHOR_ID, author.getAuthorId().intValue());
        assertEquals(AUTHOR_FIRST_NAME, author.getFirstName());
        assertEquals(AUTHOR_LAST_NAME, author.getLastName());
    }

    @Test
    public void addNewAuthor() {
        Stream<Author> authorBeforeInsert = authorDao.findAll();

        Author author = new Author();
        author.setFirstName(NEW_AUTHOR_FIRST_NAME);
        author.setLastName(NEW_AUTHOR_LAST_NAME);
        Author newAuthor = authorDao.addAuthor(author).get();
        assertNotNull(newAuthor.getAuthorId());

        Stream<Author> authorAfterInsert = authorDao.findAll();

        assertEquals(1, authorAfterInsert.count() - authorBeforeInsert.count());
    }

    @Test
    public void updateAuthor() {
        Author author = new Author();
        author.setFirstName(NEW_AUTHOR_FIRST_NAME);
        author.setLastName(NEW_AUTHOR_LAST_NAME);
        Author newAuthor = authorDao.addAuthor(author).get();
        assertNotNull(newAuthor.getAuthorId());

        author.setFirstName(NEW_AUTHOR_FIRST_NAME + "2");
        author.setLastName(NEW_AUTHOR_LAST_NAME + "2");
        authorDao.updateAuthor(author);

        Author updatedAuthor = authorDao.findById(author.getAuthorId()).get();

        assertEquals(NEW_AUTHOR_FIRST_NAME + "2", updatedAuthor.getFirstName());
        assertEquals(NEW_AUTHOR_LAST_NAME + "2", updatedAuthor.getLastName());
    }

    @Test
    public void deleteAuthor() {
        Author author = new Author();

        author.setFirstName(NEW_AUTHOR_FIRST_NAME);
        author.setLastName(NEW_AUTHOR_LAST_NAME);

        Author newAuthor = authorDao.addAuthor(author).get();
        assertNotNull(newAuthor.getAuthorId());

        authorDao.deleteAuthor(newAuthor.getAuthorId());

        Assertions.assertThrows(DataAccessException.class, () -> authorDao.findById(author.getAuthorId()));
    }
}