package pl.sda.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import pl.sda.model.BooksType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;

    @NotEmpty
    @Length(min = 2, max = 255)
    private String title;

    private LocalDate release;

    @NotEmpty
    @Length(max = 13)
    private String isbn;

    private String authorName;

    private BooksType category;

    private Integer pages;

    private Boolean borrow;

    private String borrowerName;

    private String summary;

    @NotNull
    private Long authorId;

    private Long borrowId;
}
