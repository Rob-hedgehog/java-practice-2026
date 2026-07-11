package Task6.shop.user.api;

import Task6.shop.user.api.dto.UserDto;
import Task6.shop.user.application.UserService;

import java.util.List;
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
            case "5": {
                findAll();
            }
            break;
            case "6": {
                findAllByProfileDescription();
            }
            break;
            case "7": {
                showProfileDescriptionByEmail();
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
        System.out.println("5. Показать всех пользователей");
        System.out.println("6. Показать всех пользователей c определенным описанием профиля");
        System.out.println("7. Показать описание профиля пользователя по email");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(email, name, password, profileDescription);
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
            System.out.println("Email или пароль неверны");
        }
    }

    private void findById() {
        System.out.println("Введите id пользователя");
        Integer id = scanner.nextInt();
        scanner.nextLine();
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

    private void findAll() {
        List<UserDto> users = userService.findAll();
        if (users != null) {
            for (UserDto user : users) {
                System.out.println("ID пользователя : " + user.getId() + " , Email пользователя : " + user.getEmail());
            }
        } else {
            System.out.println("Пользователи отсутствуют");
        }
    }

    private void findAllByProfileDescription() {
        System.out.println("Введите описание профиля");
        String profileDescription = scanner.nextLine();
        List<UserDto> users = userService.findAllByProfileDescription(profileDescription);
        if (users.size() != 0) {
            System.out.println("Найденные пользователи с описанием профиля \"" + profileDescription + "\" :");
            for (UserDto user : users) {
                System.out.println("ID пользователя : " + user.getId() + " , Email пользователя : " + user.getEmail());
            }
        } else {
            System.out.println("Пользователи с таким описанием профиля не найдены");
        }
    }

    private void showProfileDescriptionByEmail() {
        System.out.println("Введите email");
        String email = scanner.nextLine();
        userService.showProfileDescriptionByEmail(email);
    }



}



