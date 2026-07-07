package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.io.*;
import java.util.Optional;
import java.util.UUID;

public class UserFileRepository implements UserRepository {

    private final String fileName;
    private final UserMapper userMapper;

    public UserFileRepository(String fileName, UserMapper userMapper) {
        this.fileName = fileName;
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            String id = UUID.randomUUID().toString();
            user.setId(id);
            bw.write(userMapper.toLine(user));
            bw.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line = br.readLine();
            while (line != null) {
                User user = userMapper.fromLine(line);
                if (user.getEmail().equals(email)) {
                    return Optional.of(user);
                }
                line = br.readLine();
            }
            return Optional.empty();

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line = br.readLine();
            while (line != null) {
                User user = userMapper.fromLine(line);
                if (user.getId().equals(id)) {
                    return Optional.of(user);
                }
                line = br.readLine();
            }
            return Optional.empty();

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void changeProfileDescription(String email,String newDescription) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line = br.readLine();
            while (line != null) {
                User user = userMapper.fromLine(line);
                if (user.getEmail().equals(email)) {
                    user.setProfileDescription(newDescription);
                }
                sb.append(userMapper.toLine(user));
                sb.append("\n");
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(sb.toString());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
