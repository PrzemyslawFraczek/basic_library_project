package pl.sda.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BorrowDto {

    @NotNull
    private Long bookId;

    @NotNull
    private Long borrowerId;
}
