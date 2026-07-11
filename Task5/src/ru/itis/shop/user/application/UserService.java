package ru.itis.shop.user.application;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String email, String name, String password, String profileDescription) {
        User user = new User(email, name, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else return false;
    }

    public void findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            System.out.println("Пользователь найден : " + userOptional.get().getEmail());
        }
        else {
            System.out.println("Пользователь с таким id не найден, или id был введен неверно");
        }
    }


    public boolean checkEmailProfileDescription(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            System.out.println("Пользователь найден, текущее описание профиля : " + userOptional.get().getProfileDescription());
            return true;
        }
        System.out.println("Пользователь с таким email не найден(");
        return false;
    }

    public void changeProfileDescription(String email,String newDescription) {
        userRepository.changeProfileDescription(email,newDescription);
        System.out.println("Изменение описания профиля прошло успешно!");
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllByProfileDescription(String profileDescription) {
        return userRepository.findAllByProfileDescription(profileDescription);
    }
}
