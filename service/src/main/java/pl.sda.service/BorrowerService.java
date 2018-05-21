package pl.sda.service;

import pl.sda.dto.BorrowerDto;
import pl.sda.model.Borrower;
import pl.sda.model.BorrowerDetails;
import pl.sda.repository.BorrowerRepository;
import pl.sda.repository.IBorrowerRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BorrowerService implements IBorrowerService {

    private final IBorrowerRepository borrowerRepository;

    public BorrowerService() {
        borrowerRepository = new BorrowerRepository();
    }

    @Override
    public List<BorrowerDto> findAll() {
        List<Borrower> borrowers = borrowerRepository.findAll();

        return borrowers.stream()
                .map(b -> {
                    BorrowerDetails details = b.getBorrowerDetails();
                    String address = details != null ? details.getAddress() : null;
                    String phone = details != null ? details.getPhone() : null;
                    String email = details != null ? details.getEmail() : null;

                    return new BorrowerDto(b.getId(), b.getFirstName(), b.getLastName(), address, phone, email);
                })
                .collect(Collectors.toList());
    }
}
