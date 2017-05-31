package net.microservices.users.repository.impl;
 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import net.microservices.users.model.User;
import net.microservices.users.repository.UserRepository;
 
@Repository
public class UserRepositoryImpl implements UserRepository{
 
    private final MongoOperations mongoOperations;
 
    @Autowired
    public UserRepositoryImpl(MongoOperations mongoOperations) {
           this.mongoOperations = mongoOperations;
    }
    @Override 
    public Optional<List<User>> findAll() {
        List<User> users = this.mongoOperations.find(new Query(), User.class);
        return Optional.ofNullable(users);
    }    
    @Override
    public Optional<User> findOne(String userId) {
        User d = this.mongoOperations.findOne(new Query(Criteria.where("userId").is(userId)), User.class);
        return Optional.ofNullable(d);
    }
    @Override
    public User saveUser(User user) {
        this.mongoOperations.save(user);
        return findOne(user.getUserId()).get();
    }
    @Override 
    public void updateUser(User user) {
        this.mongoOperations.save(user);
    }
    @Override
    public void deleteUser(String userId) {
        this.mongoOperations.findAndRemove(new Query(Criteria.where("userId").is(userId)), User.class);
    }
}