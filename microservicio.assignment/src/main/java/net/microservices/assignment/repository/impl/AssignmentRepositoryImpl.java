package net.microservices.assignment.repository.impl;
 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import net.microservices.assignment.model.Assignment;
import net.microservices.assignment.repository.AssignmentRepository;
 
@Repository
public class AssignmentRepositoryImpl implements AssignmentRepository{
 
    private final MongoOperations mongoOperations;
 
    @Autowired
    public AssignmentRepositoryImpl(MongoOperations mongoOperations) {
           this.mongoOperations = mongoOperations;
    }
    @Override 
    public Optional<List<Assignment>> findByDepartmentId(String departmentId) {
        List<Assignment> users = this.mongoOperations.find(new Query(Criteria.where("departmentId").is(departmentId)), Assignment.class);
        return Optional.ofNullable(users);
    }    
    @Override
    public Optional<Assignment> findOne(String userId) {
        Assignment d = this.mongoOperations.findOne(new Query(Criteria.where("userId").is(userId)), Assignment.class);
        return Optional.ofNullable(d);
    }
    @Override
    public Assignment saveAssignment(Assignment assignment) {
        this.mongoOperations.save(assignment);
        return findOne(assignment.getUserId()).get();
    }
    @Override 
    public void updateAssignment(Assignment assignment) {
        this.mongoOperations.save(assignment);
    }
    @Override
    public void deleteAssignmentByUserId(String userId) {
        this.mongoOperations.findAndRemove(new Query(Criteria.where("userId").is(userId)), Assignment.class);
    }
}