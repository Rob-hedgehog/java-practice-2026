package ru.itis.shop.user.repository;
import ru.itis.shop.user.domain.User;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String id);

    void changeProfileDescription(String email,String newDescription);

}
