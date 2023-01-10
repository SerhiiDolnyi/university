package ua.foxminded.university.exception;

import java.io.IOException;

public class UserInputException extends Exception{
    
    public UserInputException() {        
    }
    
    public UserInputException(String message, Throwable exception) {        
        super(message, exception);
    }
    
    public UserInputException(String message) {        
        super(message);
    }
    
    public UserInputException(Throwable exception) {        
        super(exception);
    }

}
