package pl.sda.service;

import pl.sda.dto.BorrowerDto;

import java.util.List;

public interface IBorrowerService {

    List<BorrowerDto> findAll();
}
