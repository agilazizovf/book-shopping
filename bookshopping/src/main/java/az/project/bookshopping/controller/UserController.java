package az.project.bookshopping.controller;

import az.project.bookshopping.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import az.project.bookshopping.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userDAO;

    private boolean userCreated=false;

    @GetMapping(path = "/show-login")
    public String ShowLoginPage(Model model, HttpServletRequest request){
        if (userCreated){
            model.addAttribute("UserCreated","User Created Successfully");
            userCreated=false;
        }

        request.getSession().invalidate();
        return "my-custom-login";
    }


    @GetMapping(path = "/create-account")
    public String ShowCreateAccountPage(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "create-account";
    }

    @PostMapping(path = "/create-account-process")
    public String saveUser(@Valid @ModelAttribute(name="user") User user, BindingResult result, Model model){
        if(result.hasErrors()){

            return "create-account";
        }

        model.addAttribute("UserCreated","");
        boolean userExits=userDAO.createUser(user);
        if(userExits){
            model.addAttribute("UserCreated","");
            return "create-account";
        }
        userCreated=true;
        return "redirect:/show-login";

    }

}
