package az.project.bookshopping.repository;

import az.project.bookshopping.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class UserRepository {

    private final DataSource dataSource;

    // Codes with Bcrypt
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Connect with table user
    public boolean createUser(User user) {
        boolean userExits = false;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select username from users where username=?;");
            preparedStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userExits = true;
                preparedStatement.close();
            } else {
                resultSet.close();
                preparedStatement.close();
                preparedStatement = connection.prepareStatement("insert into users (username,password,enabled) values(?,?,?);");
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, "{bcrypt}"+passwordEncoder.encode(user.getPassword()));
                preparedStatement.setByte(3, (byte) 1);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                preparedStatement = connection.prepareStatement("insert into authorities (username,authority) values(?,?);");
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, "ROLE_ADMIN");
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }

            connection.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
        return userExits;
    }


}
