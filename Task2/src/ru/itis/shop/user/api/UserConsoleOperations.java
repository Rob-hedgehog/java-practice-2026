package ru.itis.shop.user.api;

import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.sql.SQLOutput;
import java.util.Optional;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                signUp();
            }
            break;
            case "2": {
                signIn();
            }
            break;
            case "3": {
               findById();
            }
            break;
            case "4": {
               changeProfileDescription();
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Изменить описание профиля");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(email, password, profileDescription);
    }


    private void signIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы вошли в приложение");
        } else {
            System.out.println("Email или пароль не верны");
        }
    }

    private void findById() {
        System.out.println("Введите id пользователя");
        String id = scanner.nextLine();
        System.out.println("Ищем пользователя по введенному id...");
        userService.findById(id);
    }

    private void changeProfileDescription() {
        System.out.println("Введите email пользователя,у которого хотите изменить описание");
        String email = scanner.nextLine();
        if (userService.checkEmailProfileDescription(email)) {
            System.out.println("Введите новое описание профиля");
            String newDescription = scanner.nextLine();
            userService.changeProfileDescription(email,newDescription);
        }

    }



}



