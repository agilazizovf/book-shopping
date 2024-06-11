package az.project.bookshopping.repository;

import az.project.bookshopping.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByPhone(String phone);

    Customer findByEmail(String email);


}
