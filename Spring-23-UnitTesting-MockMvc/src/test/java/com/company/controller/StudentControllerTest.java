package com.company.controller;
import com.company.entity.Student;
import com.company.service.StudentService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
 * 🦋 Integration Testing
 * · Once different modules are developed and integrated, integration testing is carried out. Its primary goal is to identify
 *   problems that arise during end-to-end user request processing when several modules interact with one another.
 * · In terms of a typical Spring Boot Crud application, unit tests can be written to test REST controllers, DAO layers,
 *   etc. separately. It will not require even the embedded server.
 * · In integration testing, we shall focus on testing complete request processing from the controller to the persistence layer.
 *   The application shall run inside an embedded server to create the application context and all beans. Some of these beans may be overridden to mock certain behaviors.
 *
 * 🖍️...
 * · The spring-boot-starter-test is the primary dependency that contains the majority of elements required for our tests.
 *
 * 🦋 MockMvc
 * · The Spring MockMvc class is part of the Spring test framework and helps in testing the controllers by explicitly starting a Servlet container.
 * · MockMvc is a mocked servlet environment that we can use to test our HTTP controller endpoints without the need to launch our embedded servlet (Tomcat) container.
 * · While MockMvc is a mocked environment, it still comes with HTTP semantics so that we can test the serialization, HTTP status codes, and return types of our endpoints.
 *
 * 🦋 Annotations
 * 🔹 @WebMvcTest: It is used Spring MVC controllers. It allows us to test the behavior of controllers, request mappings, and HTTP responses in a controlled and isolated
 *    environment. By using @WebMvcTest, we can narrow down the scope of testing to just the web layer, without loading the entire application context.
 *    @WebMvcTest annotation-based test runs faster because it will load only the specified controller and its dependencies without loading the entire application.
 *    Includes both the @AutoConfigureWebMvc and the @AutoConfigureMockMvc, among other functionality.
 * 🔹 @SpringBootTest: This annotation creates an application context and loads the full application context. It is used for integration testing.
 *    It will bootstrap the full application context, which means we can @Autowire any bean that's picked up by component scanning into our test.
 *    It starts the embedded server, creates a web environment, and then enables @Test methods to do integration testing.
 *    It will use SpringApplication to load the ApplicationContext. By default, it will not start a server, but it will provide a mock environment.
 * 🔹 @AutoConfigureMockMvc: It autoconfigures the MockMvc. It allows us to add a MockMvc instance to our ApplicationContext,
 *    so we can make HTTP requests against our controller.
 * 🔹 @MockBean: We use it to add mock objects to the Spring application context. The mock will replace any existing bean of the same type in the application context.
 *    If no bean of the same type is defined, a new one will be added. This annotation is useful in integration tests where a particular bean,
 *    like an external service, needs to be mocked.
 *
 * ❗️@SpringBootTest vs @WebMvcTest
 * · @SpringBootTest will load the complete application and autowire all the beans, which makes it a bit slow.
 * · @WebMvcTest is only going to scan the controller you've defined and the MVC infrastructure. So if our controller has some dependency
 *   to other beans from our service layer, the test won't start until we either load that config ourselves or provide a mock for it.
 *   This is much faster as we only load a tiny portion of your app. This annotation uses slicing.
 *
 * 🖍️...
 * · MockMvcRequestBuilders class: We build our requests by using the static methods of this class.
 * · perform(RequestBuilder requestBuilder): We execute requests by calling this method.
 * · MockMvcResultMatchers class: We write assertions for the received response by using the static methods of this class.
 * · MockMvcResultHandlers class: We use it for assertion as well.
 *
 */
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean // It Mocks the studentService beans that comes from Student Controller.
    StudentService studentService;



    @Test
    void getStudent() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\": 0, \"firstName\": \"Mike\", \"lastName\": \"Smith\", \"age\": 20}"))
                .andReturn();
    }

    @Test
    void jsonAssert() throws JSONException {
        String expected = "{\"id\": 0, \"firstName\": \"Mike\", \"lastName\": \"Smith\"}";
        String actual = "{\"id\": 0, \"firstName\": \"Mike\", \"lastName\": \"Smith\", \"age\": 20}";
        JSONAssert.assertEquals(expected, actual, false); // false means If there is a partial match, the test will pass.
    }

    @Test
    void getStudent_service() throws Exception {
        // Expected result.
        when(studentService.getStudent()).thenReturn(Arrays.asList(
                new Student("John", "Doe", 20),
                new Student("Tom", "Hanks", 50) ));

        mvc.perform(MockMvcRequestBuilders
                        .get("/service/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"id\": 0, \"firstName\": \"John\", \"lastName\": \"Doe\", \"age\": 20},{\"id\": 0, \"firstName\": \"Tom\", \"lastName\": \"Hanks\", \"age\": 50}]"))
                .andReturn();
    }






}