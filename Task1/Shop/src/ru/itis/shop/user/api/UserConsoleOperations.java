package ru.itis.shop.user.api;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.Scanner;

public class UserConsoleOperations {

    private final UserRepository userRepository;
    private final Scanner scanner;

    public UserConsoleOperations(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Войти в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("0. Выход");

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                System.out.println("Регистрация пользователя");
                System.out.println("Введите ваш email:");
                String email = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();
                System.out.println("Введите описание профиля:");
                String profileDescription = scanner.nextLine();
                User user = new User(email, password, profileDescription);
                userRepository.save(user);
            }
            break;
            case "2": {
                System.out.println("Вы можете войти в приложение");
            }
            break;
            case "3": {
                System.out.println("Введите id пользователя");
                String id = scanner.nextLine();
                System.out.println("Ищем пользователя по введенному id...");
                User user = userRepository.findById(id);
                if (user != null) {
                    System.out.println(user.getEmail());
                } else {
                    System.out.println("Пользователя с таким id не существует или id был введен неверно");
                }

            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }
}