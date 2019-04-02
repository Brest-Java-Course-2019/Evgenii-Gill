package com.epam.brest.course.web_app.rest;

import com.epam.brest.course.dao.Author;
import com.epam.brest.course.dao.AuthorDaoImpl;
import com.epam.brest.course.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service("authorRest")
public class AuthorRestClient implements AuthorService {

    @Value("${author.RestClientUrl}")
    private String url;

    @Autowired
    RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorDaoImpl.class);

    @Override
    public Stream<Author> findAll() {
        LOGGER.debug("findAllAuthors()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);

        Stream<Author> authors = (Stream<Author>) responseEntity.getBody();
        return authors;
    }

    @Override
    public final Author findById(final Integer authorId) {
        LOGGER.debug("findById");
        ResponseEntity<Author> responseEntity =
                restTemplate.getForEntity(url + "/" + authorId, Author.class);
        Author author = responseEntity.getBody();
        return author;
    }

    @Override
    public Optional<Author> addAuthor(final Author author) {
        LOGGER.debug("addAuthor({})", author);
        ResponseEntity<Author> responseEntity =
                restTemplate.postForEntity(url, author, Author.class);
        Author resultAuthor = responseEntity.getBody();
        return Optional.ofNullable(resultAuthor);
    }

    @Override
    public void updateAuthor(final Author author) {
        LOGGER.debug("updateAuthor", author);
        restTemplate.put(url, author);

    }

    @Override
    public void deleteAuthor(final Integer authorId) {
        LOGGER.debug("deleteAuthor", authorId);
        restTemplate.delete(url + "/" + authorId);
    }
}
