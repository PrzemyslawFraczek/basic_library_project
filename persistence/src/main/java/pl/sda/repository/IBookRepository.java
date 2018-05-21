package pl.sda.repository;

import pl.sda.model.Book;

import java.io.Serializable;
import java.util.List;

public interface IBookRepository {

    void save (Book book);

    List<Book> findAll();

    Book find(Long bookId);

    void edit(Book book);

    void delete(Serializable bookId);
}
