package az.project.bookshopping.service;


import az.project.bookshopping.configuration.MySession;
import az.project.bookshopping.entity.BasketBook;
import az.project.bookshopping.entity.Customer;
import az.project.bookshopping.entity.Order;
import az.project.bookshopping.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderDAO;
    private final MySession mySession;

    public OrderService(OrderRepository orderDAO, MySession mySession) {

        this.orderDAO = orderDAO;
        this.mySession = mySession;

    }

    public List<Order> save(Customer customer) {

        List<BasketBook> basketBooks = mySession.getBasketBooks();
        Order order = new Order();
        order.setCustomer(customer);
        order.setBasketBooks(basketBooks);
        order.setNote(customer.getNote());

        System.out.println(order);

        ArrayList<String> usernames = new ArrayList<>();
        for (int i = 0; i < order.getBasketBooks().size(); i++) {
            BasketBook basketBook = order.getBasketBooks().get(i);
            if (usernames.contains(basketBook.getBook().getUsername())) {
            } else {
                usernames.add(basketBook.getBook().getUsername());
            }
        }

        List<Order> orders = new ArrayList<>();

        for (String username : usernames) {
            Order newOrder = new Order();
            newOrder.setNote(order.getNote());
            newOrder.setCustomer(order.getCustomer());
            double totalPrice = 0;


            for (int j = 0; j < order.getBasketBooks().size(); j++) {
                if (order.getBasketBooks().get(j).getBook().getUsername().equals(username)) {
                    newOrder.getBasketBooks().add(order.getBasketBooks().get(j));
                    totalPrice += order.getBasketBooks().get(j).getCount() * order.getBasketBooks().get(j).getBook().getPrice();
                }
            }
            newOrder.setTotalPrice(totalPrice);
            newOrder.setUsername(username);
            orders.add(newOrder);
        }


        mySession.setBasketBooks(new ArrayList<>());
        return orderDAO.saveAll(orders);
    }
}

