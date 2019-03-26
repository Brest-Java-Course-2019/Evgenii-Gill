package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Author;
import com.epam.brest.course.service.AuthorService;
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
 * Author controller.
 */

@Controller
public class AuthorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    AuthorService authorService;

    /**
     * Show authors page.
     *
     * @return view name
     */
    @GetMapping(value = "/authors")
    public final String getAuthors(final Model model) {
        LOGGER.debug("findAll({})", model);
        model.addAttribute("authors", authorService.findAll()
                .collect(Collectors.toList()));
        return "authors";
    }

    /**
     * Show author add page.
     *
     * @param model Model
     * @return view name.
     */
    @GetMapping(value = "/author")
    public final String gotoAddAuthorPage(final Model model) {

        LOGGER.debug("gotoAddAuthorPage({})", model);
        boolean isEdit = false;
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("author", new Author());
        return "author";
    }

    /**
     * Add author to db.
     *
     * @param author Author
     * @param result BindingResult
     * @param model  Model
     * @return view name.
     */
    @PostMapping(value = "/author")
    public final String addAuthor(@Valid final Author author,
                                  final BindingResult result,
                                  final Model model) {
        LOGGER.debug("addAuthor({})", author);

        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            model.addAttribute("author", author);
            return "author";
        } else {
            Optional<Author> resultAuthor = authorService.addAuthor(author);
            LOGGER.debug("addAuthor({})", resultAuthor);
            return "redirect:/authors";
        }
    }

    /**
     * Show edit author page.
     *
     * @return view name
     */
    @GetMapping(value = "/author/{id}")
    public final String gotoEditAuthorPage(@PathVariable final Integer id, final Model model) {

        LOGGER.debug("gotoEditAuthorPage({},{})", id, model);
        Author author = authorService.findById(id);
        model.addAttribute("author", author);
        return "author";
    }

    /**
     * Update author in db.
     *
     * @param author Author
     * @param result BindingResult
     * @param model  Model
     * @return view name.
     */
    @PostMapping(value = "/editAuthor/{id}")
    public final String updateAuthor(@Valid final Author author,
                                     final BindingResult result,
                                     final Model model) {

        LOGGER.debug("updateAuthor({}, {})", author);

        if (result.hasErrors()) {
            model.addAttribute("author", author);
            model.addAttribute("isEdit", true);
            return "author";
        } else {
            authorService.updateAuthor(author);
            return "redirect:/authors";
        }
    }

    /**
     * Delete author.
     *
     * @param authorId authorId
     * @return view name
     */
    @GetMapping(value = "/author/{id}/delete")
    public final String deleteAuthor(@PathVariable(value = "id") final int authorId) {
        LOGGER.debug("deleteAuthor({})", authorId);
        authorService.deleteAuthor(authorId);
        return "redirect:/authors";
    }
}
