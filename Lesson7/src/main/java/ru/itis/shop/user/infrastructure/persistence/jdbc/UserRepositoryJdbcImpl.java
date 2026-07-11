package ru.itis.shop.user.infrastructure.persistence.jdbc;

import ru.itis.shop.infrasructure.persistence.jdbc.RowMapper;
import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final DataSource dataSource;

    private final RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet row) throws SQLException {
            return new User(row.getInt("id"),
                    row.getString("email"),
                    row.getString("name"),
                    row.getString("password"),
                    row.getString("profile_description"));
        }
    };

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("insert into " +
                    "account(email, name, password, profile_description) values(?, ?, ?, ?)")) {

                preparedStatement.setString(1,user.getEmail());
                preparedStatement.setString(2,user.getName());
                preparedStatement.setString(3,user.getPassword());
                preparedStatement.setString(4,user.getProfileDescription());

                int affectedRowsCount = preparedStatement.executeUpdate();

                if (affectedRowsCount != 1) {
                    throw new SQLException("Не получилось зарегистрировать пользователя(");
                }

                System.out.println("Регистрация успешна!");
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getId().equals(id)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }


    @Override
    public void changeProfileDescription(String email,String newDescription) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("update account set profile_description = ? where email = ?")) {

                preparedStatement.setString(1, newDescription);
                preparedStatement.setString(2, email);

                int affectedRowsCount = preparedStatement.executeUpdate();

                if (affectedRowsCount != 1) {
                    throw new SQLException("Не получилось изменить описание профиля(");
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from account")) {
                    while (resultSet.next()) {
                        users.add(userRowMapper.mapRow(resultSet));
                    }
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }

    @Override
    public List<User> findAllByProfileDescription(String profileDescription) {
        List<User> allUsers = findAll();
        List<User> filteredUsers = new ArrayList<>();
        for (User user : allUsers) {
            if (user.getProfileDescription().equals(profileDescription)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }
}
