package az.project.bookshopping.controller;

import az.project.bookshopping.configuration.MySession;
import az.project.bookshopping.entity.Book;
import az.project.bookshopping.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class CustomerController {

    private final BookRepository BookDAO;

    private final MySession mySession;


    public CustomerController(az.project.bookshopping.repository.BookRepository bookDAO, MySession mySession) {
        BookDAO = bookDAO;
        this.mySession = mySession;
    }

    @GetMapping(path = "/customer")
    public String ShowCostumerPage(Model model){
        List<Book> books = BookDAO.findAll();
        model.addAttribute("books",books);
        System.out.println("username: "+mySession.getUsername());
        return "customer";
    }

}
