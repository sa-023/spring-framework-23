package com.company.controller;
import com.company.client.EmployeeClient;
import com.company.client.UserClient;
import com.company.dto.ResponseWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 * 🔺 OpenFeign
 * 1. Add the spring-cloud-starter-openfeign dependency to pom.xml.
 * 2. Add @EnableFeignClients to the main class: With this annotation, we enable component scanning for interfaces that declare they are Feign clients.
 * 3. Create an interface and annotate it with @FeignClient, where we pass the base URI of the request.
 *    Ex: @FeignClient(url  = "https://jsonplaceholder.typicode.com/",name = "USER-CLIENT")
 *        public interface UserClient{}
 * 4. Declare the methods that consume REST endpoints in the interface. Annotate these methods with specific annotations
 *    to define the path, the HTTP method, and eventually, parameters, headers, and body of the request.
 *    We do not need to implement the methods ourselves. Based on the annotations we define with the interface methods, Spring knows to implement those methods.
 *    Ex: @GetMapping("/users")
 *        List<User> getUsers();
 * 5. To consume the Rest endpoint, call @FeignClient annotated interface methods in the Controller class whenever needed.
 *
 */
@RestController
public class Consume_FeignClient {
    private final UserClient userClient;
    private final EmployeeClient employeeClient;
    public Consume_FeignClient(UserClient userClient, EmployeeClient employeeClient) {
        this.userClient = userClient;
        this.employeeClient = employeeClient;
    }


    @GetMapping("/api/v1/users")
    public ResponseEntity<ResponseWrapper> getUsers(){
        return ResponseEntity.ok(new ResponseWrapper("UserList Retrieved", userClient.getUsers()));
    }

    @GetMapping("/api/v1/employee")
    public ResponseEntity<ResponseWrapper> getEmployee(){
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved ", employeeClient.getEmployee("6298ebfecd0551211fce37a6")));
    }





}
