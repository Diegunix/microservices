package net.microservices.department.controller;

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
import net.microservices.department.exception.DepartmentNotFoundException;
import net.microservices.department.model.Department;
import net.microservices.department.service.DepartmentService;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    private static final Log log = LogFactory.getLog(DepartmentController.class);

    private final DepartmentService departmentsService;

    @Autowired
    public DepartmentController(DepartmentService usersService) {
        this.departmentsService = usersService;
    }

    @RequestMapping(value = "/{departmentId}", method = RequestMethod.GET)
    @ApiOperation(value = "Find an department", notes = "Return a department by Id")
    public ResponseEntity<Department> departmentById(@PathVariable String departmentId)
            throws DepartmentNotFoundException {
        Department department;
        log.info("Get userById");
        try {
            department = departmentsService.findByDepartmentId(departmentId);
        } catch (DepartmentNotFoundException e) {
            department = null;
        }
        return ResponseEntity.ok(department);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Find all departments", notes = "Return all departments")
    public ResponseEntity<List<Department>> departmentById() {
        log.info("Get all Departments");
        return ResponseEntity.ok(departmentsService.findAll());
    }

    @RequestMapping(value = "/{departmentId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete an department", notes = "Delete a department by Id")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String departmentId) {
        try {
            log.info("Delete department " + departmentId);
            departmentsService.deleteDepartment(departmentId);
        } catch (DepartmentNotFoundException e) {
            log.info("The department " + departmentId + " is not exist");
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create an department", notes = "Create a new department")
    public ResponseEntity<Department> saveUser(@RequestBody @Valid Department department) {
        log.info("Save new user");
        return ResponseEntity.ok(departmentsService.saveDepartment(department));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Update an department", notes = "Update a new department")
    public ResponseEntity<Department> updateDepartment(@RequestBody @Valid Department department) {
        try {
            log.info("Update department");
            departmentsService.updateDepartment(department);
        } catch (DepartmentNotFoundException e) {
            log.info("The department " + department.getDepartmentId() + " is not exist");
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.noContent().build();
    }
}