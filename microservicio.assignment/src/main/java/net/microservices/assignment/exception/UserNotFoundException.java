package net.microservices.assignment.exception;
 
import org.springframework.core.NestedRuntimeException;
 
public class UserNotFoundException extends NestedRuntimeException {
    private static final long serialVersionUID = -2611358701974856745L;

    public UserNotFoundException(String userId) {
        super(String.format("User with  Id '%s' not founded", userId));
    }
}