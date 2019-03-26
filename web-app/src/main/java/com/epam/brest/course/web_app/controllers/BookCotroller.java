package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Book;
import com.epam.brest.course.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Book controller.
 */
@Controller
public class BookCotroller {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookCotroller.class);

    @Autowired
    BookService bookService;

    /**
     * Show books page.
     *
     * @return view name
     */
    @GetMapping(value = "/books")
    public final String getBooks(final Model model) {
        LOGGER.debug("findAll({})", model);
        model.addAttribute("books", bookService.findAll()
                .collect(Collectors.toList()));
        return "books";
    }

    /**
     * Show book add page.
     *
     * @param model Model
     * @return view name.
     */
    @GetMapping(value = "/book")
    public final String gotoAddBookPage(final Model model) {

        LOGGER.debug("gotoAddBookPage({})", model);
        boolean isEdit = false;
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("book", new Book());
        return "book";
    }

    /**
     * Add book to db.
     *
     * @param book   Book
     * @param result BindingResult
     * @param model  Model
     * @return view name.
     */
    @PostMapping(value = "/book")
    public final String addBook(@Valid final Book book,
                                final BindingResult result,
                                final Model model) {
        LOGGER.debug("addBook({})", book);

        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            model.addAttribute("book", book);
            return "book";
        } else {
            Optional<Book> resultBook = bookService.addBook(book);
            LOGGER.debug("addBook({})", resultBook);
            return "redirect:/books";
        }
    }

    /**
     * Show edit book page.
     *
     * @return view name
     */
    @GetMapping(value = "/book/{id}")
    public final String gotoEditBookPage(@PathVariable Integer id, Model model) {

        LOGGER.debug("gotoEditBookPage({},{})", id, model);
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book";
    }

    /**
     * Update book in db.
     *
     * @param book   Book
     * @param result BindingResult
     * @param model  Model
     * @return view name.
     */
    @PostMapping(value = "/editBook/{id}")
    public final String updateBook(@Valid final Book book,
                                   final BindingResult result,
                                   final Model model) {

        LOGGER.debug("updateBook{}, {})", book);

        if (result.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("isEdit", true);
            return "book";
        } else {
            bookService.updateBook(book);
            return "redirect:/books";
        }
    }

    /**
     * Delete book.
     *
     * @param bookId bookId
     * @return view name
     */
    @GetMapping(value = "/book/{id}/delete")
    public final String deleteBook(@PathVariable(value = "id") final int bookId) {
        LOGGER.debug("deleteBook({})", bookId);
        bookService.deleteBook(bookId);
        return "redirect:/books";
    }
}
