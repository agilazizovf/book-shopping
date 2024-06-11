package az.project.bookshopping.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {

    @Autowired
    DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .requestMatchers(HttpMethod.GET, "/").permitAll()
                .requestMatchers(HttpMethod.GET, "/create-account").permitAll()
                .requestMatchers(HttpMethod.POST, "/create-account-process").permitAll()
                .requestMatchers(HttpMethod.GET, "/customer").permitAll()
                .requestMatchers(HttpMethod.GET, "/rest/books").permitAll()
                .requestMatchers(HttpMethod.GET, "/styles/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/js/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/rest/orders/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/files/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/rest/books/search").permitAll()
                .requestMatchers(HttpMethod.GET, "/confirm-order").permitAll()
                .requestMatchers(HttpMethod.POST, "/rest/books/search-find-partial").permitAll()
                .requestMatchers(HttpMethod.GET, "/order-confirmation-message").permitAll()
                .requestMatchers(HttpMethod.POST, "/rest/orders/save-basket-books").permitAll()
                .requestMatchers(HttpMethod.POST, "/confirm-order-process").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/show-login")
                .loginProcessingUrl("/authenticate-user").permitAll()
                .and().logout().permitAll();
        return http.build();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }
}
