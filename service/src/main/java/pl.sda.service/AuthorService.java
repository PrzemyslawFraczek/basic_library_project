package pl.sda.service;

import pl.sda.dto.AuthorDto;
import pl.sda.model.Author;
import pl.sda.repository.AuthorRepository;
import pl.sda.repository.IAuthorRepository;

import java.util.List;

public class AuthorService implements IAuthorService {

    private final IAuthorRepository authorRepository;

    public AuthorService() {
        authorRepository = new AuthorRepository();
    }

    @Override
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Long editAuthor(Author author) throws Exception {
        return null;
    }

    @Override
    public List<AuthorDto> findAllAuthors() {
        return null;
    }

}
