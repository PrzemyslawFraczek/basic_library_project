package pl.sda.repository;

import pl.sda.model.Borrow;

public interface IBorrowRepository {

    void save(Borrow borrow);

    Borrow find(Long borrowId);

    void delete(Borrow borrow);
}
