import com.epam.brest.course.lib.Book;

import java.util.stream.Stream;

/**
 * Book service interface.
 */

public interface BookService {

    /**
     * Find all books stream.
     *
     * @return books.
     */

    Stream<Book> findAll();

    /**
     * Find book by Id from database.
     *
     * @param bookId
     * @return book
     */
    Book findById(Integer bookId);

    /**
     * Add book to database.
     *
     * @param book new book
     * @return book with id
     */
    Book addBook(Book book);

    /**
     * Update book in database.
     *
     * @param book book
     */
    void updateBook(Book book);

    /**
     * Remove book by id from database.
     *
     * @param bookId book id
     */
    void deleteBook(Integer bookId);
}