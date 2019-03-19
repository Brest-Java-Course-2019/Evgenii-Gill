import com.epam.brest.course.lib.Author;

import java.util.stream.Stream;

/**
 * Author service interface.
 */

public interface AuthorService {

    /**
     * Find all authors stream.
     *
     * @return authors .
     */

    Stream<Author> findAll();

    /**
     * Find author by Id from database.
     *
     * @param authorId
     * @return Author
     */
    Author findById(Integer authorId);

    /**
     * Add author to database.
     *
     * @param author new author
     * @return author with id
     */
    Author addAuthor(Author author);

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
