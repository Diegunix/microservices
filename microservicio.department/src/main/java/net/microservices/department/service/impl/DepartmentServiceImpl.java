package net.microservices.department.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.microservices.department.exception.DepartmentNotFoundException;
import net.microservices.department.model.Department;
import net.microservices.department.repository.DepartmentRepository;
import net.microservices.department.service.DepartmentService;

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private static final Log log = LogFactory.getLog(DepartmentServiceImpl.class);
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository userRepository) {
        this.departmentRepository = userRepository;
    }

    @Override
    public Department findByDepartmentId(String departmentId) {
        Optional<Department> user = departmentRepository.findOne(departmentId);
        if (user.isPresent()) {
            log.debug(String.format("Read departmentId '{}'", departmentId));
            return user.get();
        } else
            throw new DepartmentNotFoundException(departmentId);
    }

    @Override
    public List<Department> findAll() {
        Optional<List<Department>> department = departmentRepository.findAll();
        return department.get();
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.saveDepartment(department);
    }

    @Override
    public void updateDepartment(Department department) {
        Optional<Department> userExist = departmentRepository.findOne(department.getDepartmentId());
        if (userExist.isPresent()) {
            departmentRepository.updateDepartment(department);
        } else {
            throw new DepartmentNotFoundException(department.getDepartmentId());
        }
    }

    @Override
    public void deleteDepartment(String departmentId) {
        Optional<Department> departmentExist = departmentRepository.findOne(departmentId);
        if (departmentExist.isPresent()) {
            departmentRepository.deleteDepartment(departmentId);
        } else {
            throw new DepartmentNotFoundException(departmentId);
        }
    }
}