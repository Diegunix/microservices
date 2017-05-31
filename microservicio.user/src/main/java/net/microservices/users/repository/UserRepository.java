package net.microservices.users.repository;

import java.util.List;
import java.util.Optional;

import net.microservices.users.model.User;

public interface UserRepository {
    public Optional<List<User>> findAll();

    public Optional<User> findOne(String userId);

    public User saveUser(User user);

    public void updateUser(User user);

    public void deleteUser(String userId);
}