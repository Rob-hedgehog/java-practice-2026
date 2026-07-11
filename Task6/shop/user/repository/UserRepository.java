package Task6.shop.user.repository;
import Task6.shop.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

    void changeProfileDescription(String email,String newDescription);

    List<User> findAll();

    List<User> findAllByProfileDescription(String profileDescription);

}
