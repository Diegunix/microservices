package net.microservices.assignment.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.microservices.assignment.exception.UserNotFoundException;
import net.microservices.assignment.model.Assignment;
import net.microservices.assignment.repository.AssignmentRepository;
import net.microservices.assignment.service.AssignmentService;

@Service("assignmentService")
@Transactional
public class AssignmentServiceImpl implements AssignmentService {

    private static final Log log = LogFactory.getLog(AssignmentServiceImpl.class);
    private AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public Assignment findByUserId(String userId) {
        Optional<Assignment> user = assignmentRepository.findOne(userId);
        if (user.isPresent()) {
            log.debug(String.format("Read userId '{}'", userId));
            return user.get();
        } else
            throw new UserNotFoundException(userId);
    }

    @Override
    public List<Assignment> findByDepartmentId(String departmentId) {
        Optional<List<Assignment>> assignment = assignmentRepository.findByDepartmentId(departmentId);
        return assignment.get();
    }

    @Override
    public Assignment saveAssignment(Assignment assignment) {
        return assignmentRepository.saveAssignment(assignment);
    }

    @Override
    public void updateAssignment(Assignment assignment) {
        Optional<Assignment> userExist = assignmentRepository.findOne(assignment.getUserId());
        if (userExist.isPresent()) {
            assignmentRepository.updateAssignment(assignment);
        } else {
            throw new UserNotFoundException(assignment.getUserId());
        }
    }

    @Override
    public void deleteAssignmentByUserId(String userId) {
        Optional<Assignment> userExist = assignmentRepository.findOne(userId);
        if (userExist.isPresent()) {
            assignmentRepository.deleteAssignmentByUserId(userId);
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}