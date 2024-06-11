package az.project.bookshopping.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BasketBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer count;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
}


