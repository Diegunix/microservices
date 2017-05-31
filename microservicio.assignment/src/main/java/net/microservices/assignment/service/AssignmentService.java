package net.microservices.assignment.service;
 
import java.util.List;

import net.microservices.assignment.model.Assignment;
 
public interface AssignmentService {
     
  List<Assignment> findByDepartmentId(String departmentId);
     
  Assignment findByUserId(String userId);
   
  Assignment saveAssignment(Assignment assignment);
 
  void updateAssignment(Assignment assignment);
 
  void deleteAssignmentByUserId(String userId);
}