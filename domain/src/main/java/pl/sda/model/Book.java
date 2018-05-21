package pl.sda.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "release_date")
    private LocalDate release;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<Borrow> borrows;

    @Column(name = "borrow")
    private Boolean borrow = false;

    @Column(name = "isbn", length = 13, unique = true, nullable = false)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private BooksType category;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "summary", length = 350)
    private String summary;

    public Boolean isBorrow() {
        return borrow;
    }
}
