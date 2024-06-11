package az.project.bookshopping.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String note;

    @CreationTimestamp
    private Timestamp register;

    private Double totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<BasketBook> basketBooks;

    private String username;

    public List<BasketBook> getBasketBooks() {
        if (basketBooks==null){
            basketBooks= new ArrayList<>();
        }
        return basketBooks;
    }



}

