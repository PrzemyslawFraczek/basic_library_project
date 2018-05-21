package pl.sda.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "borrower")
@Getter
@Setter
public class Borrower implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_borrower")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "borrower_details_id")
    private BorrowerDetails borrowerDetails;

    @OneToMany(mappedBy = "borrower", fetch = FetchType.EAGER)
    private Set<Borrow> borrows;

    public String getDisplayName () {
        return this.firstName + " " + this.lastName;
    }
}
