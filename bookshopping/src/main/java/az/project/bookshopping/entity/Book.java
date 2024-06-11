package az.project.bookshopping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @NotEmpty(message = "It cannot be left empty")
    @Size(min=2, message="You need to write at least 2 characters")
    @Size (max=30, message="You need to write maximum 30 characters")
    @Column(columnDefinition = "varchar(30)")
    private String name;

    @Column(columnDefinition = "varchar(300)")
    @Size(max=300, message="You need to write maximum 300 characters")
    private String description;

    @Min(value=0, message="Minimum 0 can be written")
    @Max(value=1000, message="Maximum 1000 can be written")
    @NotNull(message="You need to write a price")
    private double price;

    @Column(columnDefinition = "varchar(300)")
    @Size(max=30, message="You must write a maximum of 30 characters")
    private String author;

    @Min(value=0, message="Minimum 0 can be written")
    @Max(value=10000, message="Maximum 10000 can be written")
    private Integer pageCount;
    private String image;
    private String username;

}
