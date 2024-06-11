package az.project.bookshopping.repository;

import az.project.bookshopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    public List<Order> findAllByUsername(String username);

}
