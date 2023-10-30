package org.epam.resttask.cloud.service;

import org.epam.resttask.cloud.jpa.UserRepository;
import org.epam.resttask.dto.User;
import org.epam.resttask.service.SubscriptionService;
import org.epam.resttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SubscriptionService subscriptionService;

    private static final String USER_DOES_NOT_EXIST_MESSAGE = "User %s does not exist.";

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        checkUserExists(user.getId());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        checkUserExists(id);
        subscriptionService.deleteAllSubscriptionsByUserId(id);
        userRepository.deleteById(id);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException(String.format(USER_DOES_NOT_EXIST_MESSAGE, id)));
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    private void checkUserExists(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityDoesNotExistException(String.format(USER_DOES_NOT_EXIST_MESSAGE, id));
        }
    }
}
