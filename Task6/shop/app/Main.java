package Task6.shop.app;

import Task6.shop.infrasructure.persistence.jdbc.DriverManagerDataSource;
import Task6.shop.user.api.UserConsoleOperations;
import Task6.shop.user.application.UserService;
import Task6.shop.user.infrastructure.persistence.jdbc.UserRepositoryJdbcImpl;
import Task6.shop.user.repository.UserRepository;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/shop_db","robsibe","27102007");
        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        UserService userService = new UserService(userRepository);
        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }
}
