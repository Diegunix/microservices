package net.microservices.department.repository;

import java.util.List;
import java.util.Optional;

import net.microservices.department.model.Department;

public interface DepartmentRepository {
    public Optional<List<Department>> findAll();

    public Optional<Department> findOne(String departmentId);

    public Department saveDepartment(Department department);

    public void updateDepartment(Department department);

    public void deleteDepartment(String departmentId);
}