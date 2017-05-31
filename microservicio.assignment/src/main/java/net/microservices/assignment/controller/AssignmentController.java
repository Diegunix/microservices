package net.microservices.assignment.controller;

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
import net.microservices.assignment.exception.UserNotFoundException;
import net.microservices.assignment.model.Assignment;
import net.microservices.assignment.service.AssignmentService;

@RestController
@RequestMapping("assignment")
public class AssignmentController {

    private static final Log log = LogFactory.getLog(AssignmentController.class);

    private final AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService usersService) {
        this.assignmentService = usersService;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "Find an assignment", notes = "Return a assignment by userId")
    public ResponseEntity<Assignment> userById(@PathVariable String userId) throws UserNotFoundException {
        Assignment user;
        log.info("Get assignment by userId");
        try {
            user = assignmentService.findByUserId(userId);
        } catch (UserNotFoundException e) {
            user = null;
        }
        return ResponseEntity.ok(user);

    }

    @RequestMapping(value = "/department/{departmentId}",method = RequestMethod.GET)
    @ApiOperation(value = "Find all assignment by department", notes = "Return all assignment by department")
    public ResponseEntity<List<Assignment>> userByDepartmentId(@PathVariable String departmentId) {
        log.info("Get all assignment by "+ departmentId);
        return ResponseEntity.ok(assignmentService.findByDepartmentId(departmentId));
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete an assignment", notes = "Delete a assignment by userId")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        try {
        log.info("Delete assignment " + userId);
        assignmentService.deleteAssignmentByUserId(userId);
        } catch (UserNotFoundException e) {
            log.info("The assignment by user "+ userId +" is not exist");
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create an assignment", notes = "Create a new assignment")
    public ResponseEntity<Assignment> saveAssignment(@RequestBody @Valid Assignment assignment) {
        log.info("Save new assignment");
        return ResponseEntity.ok(assignmentService.saveAssignment(assignment));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Update an assignment", notes = "Update a new assignment")
    public ResponseEntity<Assignment> updateAssignment(@RequestBody @Valid Assignment assignment) {
        try {
            log.info("Update assignment");
        assignmentService.updateAssignment(assignment);
        } catch (UserNotFoundException e) {
            log.info("The assignment by user "+ assignment.getUserId() +" is not exist");
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.noContent().build();
    }
}