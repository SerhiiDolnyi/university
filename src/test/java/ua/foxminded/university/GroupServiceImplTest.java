package ua.foxminded.university;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.foxminded.university.exception.RepositoryException;
import ua.foxminded.university.exception.UserInputException;
import ua.foxminded.university.service.group.GroupServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GroupServiceImplTest {
    
    @Mock
    GroupServiceImpl groupServiceImpl;
    
    @Test
    void deleteTest_ShouldThrowDataaccessException() throws UserInputException {
        doThrow(new RepositoryException("Error in database operations"))
            .when(groupServiceImpl).delete(0);
        Throwable exception = assertThrows(RepositoryException.class, () -> 
            groupServiceImpl.delete(0));
        assertThat(exception.getMessage(), is("Error in database operations"));
        verify(groupServiceImpl).delete(0);        
    }
}
