package pl.sda.repository;

import pl.sda.model.Author;

public interface IAuthorRepository {

    void save(Author author);

    Author find(Long authorId);
}
