package net.microservices.users.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.microservices.users.exception.UserNotFoundException;
import net.microservices.users.model.User;
import net.microservices.users.service.UserService;

@RestController
@RequestMapping("users")
public class UsersController {

    private static final Log log = LogFactory.getLog(UsersController.class);

    private final UserService usersService;

    @Autowired
    public UsersController(UserService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "Find an user", notes = "Return a user by Id")
    public ResponseEntity<User> userById(@PathVariable String userId) throws UserNotFoundException {
        User user;
        log.info("Get userById");
        try {
            user = usersService.findByUserId(userId);
        } catch (UserNotFoundException e) {
            user = null;
        }
        return ResponseEntity.ok(user);

    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Find all user", notes = "Return all users")
    public ResponseEntity<List<User>> userById() {
        log.info("Get allUsers");
        return ResponseEntity.ok(usersService.findAll());
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete an user", notes = "Delete a user by Id")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        try {
        log.info("Delete user " + userId);
        usersService.deleteUser(userId);
        } catch (UserNotFoundException e) {
            log.info("The user "+ userId +" is not exist");
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create an user", notes = "Create a new user")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
        log.info("Save new user");
        return ResponseEntity.ok(usersService.saveUser(user));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Update an user", notes = "Update a new user")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user) {
        try {
            log.info("Update user");
        usersService.updateUser(user);
        } catch (UserNotFoundException e) {
            log.info("The user "+ user.getUserId() +" is not exist");
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.noContent().build();
    }
}