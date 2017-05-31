package net.microservices.assignment.repository;

import java.util.List;
import java.util.Optional;

import net.microservices.assignment.model.Assignment;

public interface AssignmentRepository {
    public Optional<List<Assignment>> findByDepartmentId(String departmentId);

    public Optional<Assignment> findOne(String userId);

    public Assignment saveAssignment(Assignment assignment);

    public void updateAssignment(Assignment assignment);

    public void deleteAssignmentByUserId(String userId);
}