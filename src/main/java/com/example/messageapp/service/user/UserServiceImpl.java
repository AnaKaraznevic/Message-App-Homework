package com.example.messageapp.service.user;

import com.example.messageapp.exception.UserNotFoundException;
import com.example.messageapp.model.Role;
import com.example.messageapp.model.User;
import com.example.messageapp.repository.MessageRepository;
import com.example.messageapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User by id " + userId + " not found."));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email)
                .orElse(null);
    }

    @Override
    public int createUser(User user) {
        return userRepository.save(user.getName(), user.getEmail(), Role.REGULAR.toString());
    }

    @Override
    @Transactional
    public void deleteAndAnonymizeUser(Long userId) {
        var user = getUserById(userId);
        userRepository.deleteUser(user.getId());
        messageRepository.anonymizeMessagesByUserId(user.getId(), User.ANONYMOUS_USER_ID);
    }
}
