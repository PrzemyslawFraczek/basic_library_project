package pl.sda.service;

import lombok.extern.slf4j.Slf4j;
import pl.sda.dto.BorrowDto;
import pl.sda.exception.BorrowNotFoundException;
import pl.sda.exception.BorrowerNotFoundException;
import pl.sda.exception.ItemNotFoundException;
import pl.sda.model.Book;
import pl.sda.model.Borrow;
import pl.sda.model.Borrower;
import pl.sda.repository.BookRepository;
import pl.sda.repository.BorrowRepository;
import pl.sda.repository.BorrowerRepository;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
public class BorrowService implements IBorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    public BorrowService() {
        this.borrowRepository = new BorrowRepository();
        this.bookRepository = new BookRepository();
        this.borrowerRepository = new BorrowerRepository();
    }

    @Override
    public void save(BorrowDto borrowDto) throws BorrowerNotFoundException, ItemNotFoundException, IllegalAccessException {
        Long borrowerId = borrowDto.getBorrowerId();
        Long bookId = borrowDto.getBookId();

        Borrower borrower = Optional.ofNullable(borrowerRepository.find(borrowerId))
                .orElseThrow(() -> {
                    log.error("[Can not find borrower] Id: {}", borrowerId);
                    return new BorrowerNotFoundException("Can not find borrower");
                });

        Book book = Optional.ofNullable(bookRepository.find(bookId))
                .orElseThrow(() -> {
                    log.error("[Can not find item] Id: {}", bookId);
                    return new ItemNotFoundException("Can not find item");
                });

        if (book.isBorrow()) {
            log.error("[Item has already been borrowed] Id: {}", bookId);
            throw new IllegalAccessException("Item has already been borrowed");
        }

        Borrow borrow = setUpBorrow(borrower, book);
        borrowRepository.save(borrow);

        book.setBorrow(true);
        bookRepository.edit(book);
    }

    @Override
    public void delete(Long id) throws BorrowNotFoundException {
        Borrow borrow = Optional.ofNullable(borrowRepository.find(id))
                .orElseThrow(() -> {
                    log.error("[]Can not find borrower] Id: {}", id);
                    return new BorrowNotFoundException("Can not find borrow");
                });

        Book book = borrow.getBook();
        book.setBorrow(false);
        bookRepository.edit(book);

        borrowRepository.delete(borrow);
        log.info("[Borrow has been deleted] Id: {}", id);
    }

    private Borrow setUpBorrow(Borrower borrower, Book book) {
        Borrow borrow = new Borrow();
        borrow.setBorrower(borrower);
        borrow.setBook(book);
        borrow.setRentalDate(LocalDate.now());
        return borrow;
    }
}
