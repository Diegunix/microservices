package net.microservices.users.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.microservices.users.exception.UserNotFoundException;
import net.microservices.users.model.User;
import net.microservices.users.repository.UserRepository;
import net.microservices.users.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserId(String userId) {
        Optional<User> user = userRepository.findOne(userId);
        if (user.isPresent()) {
            log.debug(String.format("Read userId '{}'", userId));
            return user.get();
        } else
            throw new UserNotFoundException(userId);
    }

    @Override
    public List<User> findAll() {
        Optional<List<User>> user = userRepository.findAll();
        return user.get();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        Optional<User> userExist = userRepository.findOne(user.getUserId());
        if (userExist.isPresent()) {
            userRepository.updateUser(user);
        } else {
            throw new UserNotFoundException(user.getUserId());
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<User> userExist = userRepository.findOne(userId);
        if (userExist.isPresent()) {
            userRepository.deleteUser(userId);
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}