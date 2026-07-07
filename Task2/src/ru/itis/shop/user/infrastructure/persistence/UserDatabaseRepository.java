package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.Optional;

public class UserDatabaseRepository implements UserRepository {

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
}
