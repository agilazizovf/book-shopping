package az.project.bookshopping.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    @Size(min = 1, message = "Username must be at least 1 character")
    @Size(max = 30, message = "Username must be maximum 30 character")
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @Size(min = 1, message = "Password must be at least 1 character")
    @Size(max = 30, message = "Password must be maximum 30 character")
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
