package az.project.bookshopping.controller;

import az.project.bookshopping.configuration.MySession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private MySession mySession;

    @GetMapping(path = {"/home","/"})
    public String ShowHomePage(Model model){

        System.out.println(mySession.getBasketBooks());

        String username=mySession.getUsername();

        if(username.equals("anonymousUser")){

        }
        else{
            model.addAttribute("username",username);
        }

        return "home";
    }




}
