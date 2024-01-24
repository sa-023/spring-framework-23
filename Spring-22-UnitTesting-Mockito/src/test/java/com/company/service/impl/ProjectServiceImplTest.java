package com.company.service.impl;
import com.company.dto.ProjectDTO;
import com.company.entity.Project;
import com.company.mapper.ProjectMapper;
import com.company.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/*
 * 🦋 Unit Testing
 * · It is a software testing that focuses on individual units or components of a software system.
 * · The purpose of unit testing is to validate that each unit of the software works as intended and meets the requirements.
 *
 * 🦋 Mockito
 * · It is a java based mocking framework, used in conjunction with other testing frameworks such as Junit and TestNG.
 * · It internally uses Java Reflection API and allows to create objects of a service. A mock object returns a dummy data and avoids external
 *   dependencies. It simplifies the development of tests by mocking external dependencies and apply the mocks into the code under test.
 * · Mockito Dependencies: · mockito-core · mockito-junit-jupiter
 *   Note that mockito-junit-jupiter is required for JUnit 5, if you are using any other testing framework such as JUnit 4
 *   or TestNG then you remove this dependency and include only mockito-core dependency.
 * 📌 Mockito Limitations: · mock final classes · mock enums · mock final methods · mock static methods · mock private methods · mock hasCode() and equals()
 *
 * 🖍️...
 * · There is 2 ways to mock:
 * 1. We pass our class that we would like to mock inside the static mock() method.
 *    Ex: UserServiceImpl mock = Mockito.mock(UserServiceImpl.class);
 * 2. We annotate the field (instance) with the @Mock annotation we would like to mock.
 *    Ex: @Mock
 *        private UserServiceImpl;
 *
 * 🦋 Annotations
 * 🔹 @ExtendWith(MockitoExtension.class): It is a Junit annotation that is used to register custom extensions. For Mockito,
 *    this means using MockitoExtension.class with @ExtendWith to initialize mocks and leverage Mockito's features in JUnit 5 tests.
 * 🔹 @Mock: This annotation creates a mock implementation of the repository (ex: ProjectRepository) interface.
 * 🔹 @InjectMocks: It is used to inject mock fields into the service instance.
 *
 * 🦋 Steps
 * 1. We use the @ExtendWith(MockitoExtension.class) annotation at the class level to tell JUnit 5 to initialize mocks and prepare them for injection.
 * 2. The @Mock annotation creates a mock implementation of the ProjectRepository interface.
 * 3. @InjectMocks is used to inject mock fields into the service instance.
 * 4. In the test method getByProjectCode_test(), we define the behavior of the mock (when(projectRepository.findByProjectCode(anyString())).thenReturn(project);)
 *    and then execute the service method to be tested.
 * 5. Finally, we assert that the returned employee matches the expected value.
 *
 *
 * 🖍️...
 * · One of the basic functions of mocking frameworks is an ability to return a given value when a specific method is called. It can be done using
 *   Mockito.when() in conjunction with thenReturn(). This process of defining how a given mock method should behave is called stubbing.
 *   Ex: when(projectRepository.findByProjectCode(anyString())).thenReturn(project);
 * · thenReturn(T valueToBeReturned): Returns given value.
 * · thenThrow(Throwable toBeThrown): Throws given exception.
 *   thenThrow(Class<? extends Throwable> toBeThrown): Throws given exception.
 * · then(Answer answer): User created code to answer.
 *   thenAnswer(Answer answer): User created code to answer.
 * · thenCallRealMethod(): Calls real method when working with partial mock/spy.
 *
 */
@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {
    @Mock
    ProjectRepository projectRepository;
    @Mock
    ProjectMapper projectMapper;
    @InjectMocks
    ProjectServiceImpl projectService;


    @Test
    void getByProjectCode_test() {
        // Given
        Project project = new Project();
        ProjectDTO projectDTO = new ProjectDTO();
        when(projectRepository.findByProjectCode(anyString())).thenReturn(project); // anyString() method comes from Mockito.
        when(projectMapper.convertToDto(project)).thenReturn(projectDTO);
        // When
        ProjectDTO projectDTO1 = projectService.getByProjectCode(anyString()); // Testing
        // Then
        verify(projectRepository).findByProjectCode(anyString());
        verify(projectMapper).convertToDto(any(Project.class));
        assertNotNull(projectDTO1); // assertNotNull() method comes from Junit.
    }

    @Test
    void getByProjectCode_exception_test() {
        // we define the behavior of the mock and then execute the service method to be tested.
        when(projectRepository.findByProjectCode("")).thenThrow(new RuntimeException("Project Not Found"));
        Throwable exception = assertThrows(RuntimeException.class, () -> projectService.getByProjectCode("PR01"));
//        verify(projectRepository).findByProjectCode(anyString());
        assertEquals("Project Not Found", exception.getMessage()); //  Finally, we assert that the returned employee matches the expected value.
    }

    @Test
    void save_test() {
        // Given
        ProjectDTO projectDTO = new ProjectDTO();
        Project project = new Project();
        when(projectMapper.convertToEntity(projectDTO)).thenReturn(project);
        when(projectRepository.save(project)).thenReturn(project);
        // When
        projectService.save(projectDTO); // Testing
        // Then
        verify(projectRepository).save(project);
        verify(projectMapper).convertToEntity(any(ProjectDTO.class));
    }








}