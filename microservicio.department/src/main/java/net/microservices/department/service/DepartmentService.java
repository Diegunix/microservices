package net.microservices.department.service;
 
import java.util.List;

import net.microservices.department.model.Department;
 
public interface DepartmentService {
     
  List<Department> findAll();
     
  Department findByDepartmentId(String departmentId);
   
  Department saveDepartment(Department department);
 
  void updateDepartment(Department department);
 
  void deleteDepartment(String departmentId);
}