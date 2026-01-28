package com.ashique.blog.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @GetMapping("/id/{id}")
    User getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.getUserById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    @GetMapping("/username/{username}")
    User getUserByUsername(@PathVariable String username) {
        Optional<User> user = userRepository.getUserByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    @GetMapping("/email/{email}")
    User getUserByEmail(@PathVariable String email) {
        Optional<User> user = userRepository.getUserByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    void createUser(@RequestBody User user) {
        try {
            userRepository.createUser(user);
        } catch (Exception e) {
            throw new RuntimeException("A user with the same identifier already exists.");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void updateUser(@RequestBody User user, @PathVariable int id) {
        userRepository.updateUser(user, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable int id) {
        userRepository.deleteUser(id);
    }
}
