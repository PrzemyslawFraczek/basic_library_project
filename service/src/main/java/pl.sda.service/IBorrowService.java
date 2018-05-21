package pl.sda.service;

import pl.sda.dto.BorrowDto;
import pl.sda.exception.BorrowNotFoundException;
import pl.sda.exception.BorrowerNotFoundException;
import pl.sda.exception.ItemNotFoundException;

public interface IBorrowService {

    void save(BorrowDto borrowDto) throws BorrowerNotFoundException, ItemNotFoundException, IllegalAccessException;

    void delete(Long id) throws BorrowNotFoundException;
}
