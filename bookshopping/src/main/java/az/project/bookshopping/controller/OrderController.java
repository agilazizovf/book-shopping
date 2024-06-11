package az.project.bookshopping.controller;


import az.project.bookshopping.configuration.MySession;
import az.project.bookshopping.entity.Customer;
import az.project.bookshopping.repository.CustomerRepository;
import az.project.bookshopping.repository.OrderRepository;
import az.project.bookshopping.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private final OrderService orderService;

    private final OrderRepository orderDAO;

    private final CustomerRepository customerDAO;

    private final MySession mySession;

    public OrderController(OrderRepository orderDAO, MySession mySession, OrderService orderService, CustomerRepository customerDAO) {
        this.orderDAO = orderDAO;

        this.mySession = mySession;
        this.orderService = orderService;
        this.customerDAO = customerDAO;

    }

    @GetMapping(path = "/orders")
    public String showOrdersPage(Model model){
        model.addAttribute("orders", orderDAO.findAllByUsername(mySession.getUsername()));
        return "orders";
    }

    @GetMapping(path = "/confirm-order")
    public String showConfirmOrderPage(Model model){

        Customer customer= new Customer();
        model.addAttribute("customer",customer);

        return "confirm-order";
    }



    @GetMapping(path = "/order-confirmation-message")
    public String showOrderConfirmationPage(){

        return "order-confirmation-message";
    }

    @PostMapping(path = "/confirm-order-process")
    public String showConfirmOrderProcess(@Valid @ModelAttribute(name = "customer") Customer customer, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "confirm-order";
        }

        Customer customerFindByPhone = customerDAO.findByPhone(customer.getPhone());

        if (customerFindByPhone==null) {

            Customer customerFindByEmail = customerDAO.findByEmail(customer.getEmail());
            if (customerFindByEmail==null) {
                    //customerDAO.save(customer);
                  // customer=customerDAO.findByPhone(customer.getPhone());
            }
            else{
                Integer id = customerFindByEmail.getId();
                customer.setId(id);
                customerDAO.save(customer);
                customer=customerDAO.findById(id).get();
            }

        }
        else{Integer id = customerFindByPhone.getId();
            customer.setId(id);
            customerDAO.save(customer);
            customer=customerDAO.findById(id).get();
        }

        orderService.save(customer);

        return "redirect:/order-confirmation-message" ;

    }

}