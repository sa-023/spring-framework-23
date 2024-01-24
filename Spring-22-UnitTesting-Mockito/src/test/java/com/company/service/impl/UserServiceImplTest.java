package com.company.service.impl;
import com.company.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // This annotation comes from Junit, MockitoExtension. class comes from mockito. It will allow us to use the mockito library in our test.
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks // It injects a mock UserRepository into UserServiceImpl instead of a real one.
    UserServiceImpl userService;


    @Test
    void deleteByUsername_test() {
        userService.deleteByUserName("mikesmith@company.com"); // testing

//        Mockito.verify(userRepository).deleteByUserName("mikesmith@company.com"); // Verify the deleteByUserName() method runs as expected.
//        verify(userRepository, times(2)).deleteByUserName("mikesmith@company.com");
//        verify(userRepository, atLeastOnce()).deleteByUserName("mikesmith@company.com");
//        verify(userRepository, atLeast(5)).deleteByUserName("mikesmith@company.com");
//        verify(userRepository, atMostOnce()).deleteByUserName("mikesmith@company.com");
//        verify(userRepository, atMost(5)).deleteByUserName("mikesmith@company.com");

        InOrder inOrder = inOrder(userRepository); // The InOrder interface comes from Mockito. It checks the lines of the code in order in the deleteByUsername() method.
        inOrder.verify(userRepository).deleteByUserName("mikesmith@company.com"); // Oder - 1
        inOrder.verify(userRepository).findAll(); // Oder - 2
        /*
         * 🖍️...
         * · times(2): Expect method to run twice.
         * · atLeastOnce(): Expect the method to run at least once.
         * · atLeast(5): Expect the method to run at least five times.
         * · atMostOnce(): Expect the method to run at most once.
         * · atMost(5): Expect the method to run at most five times.
         * · InOrder interface: It comes from Mockito. It checks the lines of the code in order in the method.
        */
    }






}