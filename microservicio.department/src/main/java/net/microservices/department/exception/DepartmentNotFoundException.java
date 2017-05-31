package net.microservices.department.exception;
 
import org.springframework.core.NestedRuntimeException;
 
public class DepartmentNotFoundException extends NestedRuntimeException {
    private static final long serialVersionUID = 1553426248007985318L;

    public DepartmentNotFoundException(String departmentId) {
        super(String.format("Department with  Id '%s' not founded", departmentId));
    }
}