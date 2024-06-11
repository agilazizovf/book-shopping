package az.project.bookshopping.rest;


import az.project.bookshopping.configuration.MySession;
import az.project.bookshopping.entity.BasketBook;
import az.project.bookshopping.entity.Order;
import az.project.bookshopping.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path="/rest/orders")
public class OrderRestController {


    private final OrderRepository orderDAO;

    private final MySession mySession;

    public OrderRestController(OrderRepository orderDAO, MySession mySession) {
        this.orderDAO = orderDAO;
        this.mySession = mySession;
    }

    @GetMapping
    public List<Order> findAll(){
        return orderDAO.findAll();
    }

    @PostMapping
    public List<Order> saveOrder(@RequestBody Order order){

        List<String> usernames = new ArrayList<>();
        for(int i=0;i<order.getBasketBooks().size();i++){
            BasketBook basketBook = order.getBasketBooks().get(i);
            if(usernames.contains(basketBook.getBook().getUsername())){}
            else{
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

        return orderDAO.saveAll(orders);
    }

    @GetMapping(path="/{id}")
    public Order findById(@PathVariable(name = "id") Integer id){
        return orderDAO.findById(id).get();
    }

    @GetMapping(path="/{username}")
    public List<Order> findByUsername(@PathVariable(name = "username") String username){
        return orderDAO.findAllByUsername(username);
    }

    @PostMapping(path="/save-basket-books")
    public Boolean saveBasketArrayGlobal(@RequestBody List<BasketBook> basketArrayGlobal){
        System.out.println(basketArrayGlobal);
        mySession.setBasketBooks(basketArrayGlobal);
        return true;
    }



}