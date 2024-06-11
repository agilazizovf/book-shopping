package az.project.bookshopping.configuration;

import az.project.bookshopping.entity.BasketBook;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MySession {
    private String username;

    private List<BasketBook> basketBooks;

    public MySession() {
        Authentication loggedInUser= SecurityContextHolder.getContext().getAuthentication();
        if(loggedInUser!=null){
            this.username=loggedInUser.getName();
        }

    }

    @PostConstruct
    public void initSession(){
        System.out.println("MySession>InitVersion");
    }

    @PreDestroy
    public void destroySession(){
        System.out.println("MySession>DestroyVersion");
    }


}

