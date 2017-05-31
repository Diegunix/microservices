package net.microservices.department.repository.impl;
 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import net.microservices.department.model.Department;
import net.microservices.department.repository.DepartmentRepository;
 
@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository{
 
    private final MongoOperations mongoOperations;
 
    @Autowired
    public DepartmentRepositoryImpl(MongoOperations mongoOperations) {
           this.mongoOperations = mongoOperations;
    }
    @Override 
    public Optional<List<Department>> findAll() {
        List<Department> users = this.mongoOperations.find(new Query(), Department.class);
        return Optional.ofNullable(users);
    }    
    @Override
    public Optional<Department> findOne(String departmentId) {
        Department d = this.mongoOperations.findOne(new Query(Criteria.where("departmentId").is(departmentId)), Department.class);
        return Optional.ofNullable(d);
    }
    @Override
    public Department saveDepartment(Department department) {
        this.mongoOperations.save(department);
        return findOne(department.getDepartmentId()).get();
    }
    @Override 
    public void updateDepartment(Department department) {
        this.mongoOperations.save(department);
    }
    @Override
    public void deleteDepartment(String departmentId) {
        this.mongoOperations.findAndRemove(new Query(Criteria.where("departmentId").is(departmentId)), Department.class);
    }
}