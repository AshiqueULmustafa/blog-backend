package com.ashique.blog.user;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void createUser(User user) {
        var updated = jdbcClient.sql(
                "INSERT INTO `User`(id, username, email, password, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)")
                .params(List.of(user.id(), user.username(), user.email(), user.password(), user.createdAt(),
                        user.updatedAt()))
                .update();

        Assert.state(updated == 1, "Failed to create user " + user.username());
    }

    public void updateUser(User newUser, int id) {
        var updated = jdbcClient.sql(
                "UPDATE `User` SET username = ?, email = ?, password = ?, created_at = ?, updated_at = ? WHERE id = ?")
                .params(List.of(newUser.username(), newUser.email(), newUser.password(), newUser.createdAt(),
                        newUser.updatedAt(), id))
                .update();

        Assert.state(updated == 1, "Failed to update user " + newUser.username());
    }

    public Optional<User> getUserById(int id) {
        return jdbcClient.sql("SELECT id, username, email, password, created_at, updated_at FROM `User` WHERE id = :id")
                .param("id", id)
                .query(User.class)
                .optional();
    }

    public List<User> getAllUsers() {
        return jdbcClient.sql("SELECT * FROM `User`")
                .query(User.class)
                .list();
    }

    public Optional<User> getUserByUsername(String username) {
        return jdbcClient.sql(
                "SELECT id, username, email, password, created_at, updated_at FROM `User` WHERE username = :username")
                .param("username", username)
                .query(User.class)
                .optional();
    }

    public Optional<User> getUserByEmail(String email) {
        return jdbcClient
                .sql("SELECT id, username, email, password, created_at, updated_at FROM `User` WHERE email = :email")
                .param("email", email)
                .query(User.class)
                .optional();
    }

    public void deleteUser(int id) {
        var updated = jdbcClient.sql("DELETE FROM `User` WHERE id = ?")
                .param(id)
                .update();

        Assert.state(updated == 1, "Failed to delete user with id " + id);
    }
}
