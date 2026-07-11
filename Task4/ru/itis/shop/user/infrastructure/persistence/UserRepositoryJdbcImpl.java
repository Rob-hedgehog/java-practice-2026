package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/shop_db";
    private static final String DB_USER = "robsibe";
    private static final String DB_PASSWORD = "27102007";

    @Override
    public void save(User user) {
        System.out.println("Сохраняем пользователя в базу данных...");
    }

    @Override
    public Optional<User> findById(String id) {
        System.out.println("Ищем полььзователя по айди в базе данных...");
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        System.out.println("Ищем пользователя по email в базе данных...");
        return null;
    }


    @Override
    public void changeProfileDescription(String email,String newDescription) {
        System.out.println("Изменение описания профиля в базе данных...");
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from account")) {
                    while (resultSet.next()) {
                        users.add(new User(String.valueOf(resultSet.getInt("id")),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("profile_description")));
                    }
                }
            }
            return users;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
