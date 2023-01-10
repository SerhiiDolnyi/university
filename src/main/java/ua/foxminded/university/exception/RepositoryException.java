package ua.foxminded.university.exception;

import org.springframework.dao.DataAccessException;

public class RepositoryException extends DataAccessException {
    
    public RepositoryException(String message, Throwable exception) {        
        super(message, exception);
    }
    
    public RepositoryException(String message) {        
        super(message);
    }    
}
