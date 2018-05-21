package pl.sda.service;

import pl.sda.dto.AuthorDto;
import pl.sda.model.Author;

import java.util.List;

public interface IAuthorService {

    void saveAuthor(Author author);

    Long editAuthor(Author author) throws Exception;

    List<AuthorDto> findAllAuthors();
}
