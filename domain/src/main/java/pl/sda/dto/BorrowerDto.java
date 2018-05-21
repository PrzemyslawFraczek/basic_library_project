package pl.sda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
}
