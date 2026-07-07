package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.io.*;
import java.util.UUID;

public class UserFileRepository implements UserRepository {

    private final String filename;

    public UserFileRepository(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))){
            String id = UUID.randomUUID().toString();
            user.setId(id);
            bw.write(user.getId() + "|" +
                    user.getEmail() + "|" +
                    user.getPassword() + "|" +
                    user.getProfileDescription());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User findById(String id) {
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while (line != null) {
                String[] userData = line.split("\\|");
                User user = new User(userData[0],userData[1],userData[2],userData[3]);
                if (user.getId().equals(id)) {
                    return user;
                } else {
                    line = br.readLine();
                }
            }
        } catch( IOException e) {
            throw new IllegalArgumentException();
        }
        return null;
    }
}
