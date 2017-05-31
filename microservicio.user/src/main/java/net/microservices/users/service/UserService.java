package net.microservices.users.service;
 
import java.util.List;

import net.microservices.users.model.User;
 
public interface UserService {
     
  List<User> findAll();
     
  User findByUserId(String userId);
   
  User saveUser(User user);
 
  void updateUser(User user);
 
  void deleteUser(String userId);
}