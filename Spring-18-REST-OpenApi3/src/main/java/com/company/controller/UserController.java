package com.company.controller;
import com.company.entity.User;
import com.company.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/*
 * 🦋 OpenAPI Specification (OAS)
 * · The OpenAPI Specification(OAS), is the world’s standard for defining RESTful interfaces.
 * · The OAS enables developers to design a technology-agnostic API interface that forms the basis of their API development and consumption.
 *
 * 🦋 Swagger
 * · Swagger is the name associated with some of the most well-known, and widely used tools for implementing the OpenAPI specification.
 * · OpenAPI is a specification, and Swagger is a tool for implementing the OpenAPI specification.
 *
 * 🦋 The ways to create OpenAPI specifications are:
 * 1. We can create specification documents manually by creating YAML files in SwaggerHub.com.
 * 2. We can add the springdoc-openapi-ui dependency to the pom.xml file to automate the generation of Spring-based REST APIs in OpenAPI 3.0 format.
 *    2.1. Run our application and find the OpenAPI descriptions at /v3/api-docs, which is the default path: http://localhost:8080/v3/api-docs
 *         We can customize the path in application.properties: springdoc.api-docs.path=/api-docs
 * 🖍️... The OpenAPI definitions are in JSON format by default. For yaml format, we can obtain the definitions at: http://localhost:8080/api-docs.yaml
 *    2.2. Integration with Swagger UI: http://localhost:8080/swagger-ui/index.html (or /swagger-ui.html)
 *         We can customize the path in application.properties: springdoc.swagger-ui.path=/swagger-custom.html
 *    2.3. If we would like to modify our swagger documentation, we should add customOpenApi @Bean to the main class. And add annotations to controller methods.
 * 🖍️...
 * · @Tag(name = "User", description = "User CRUD Operations"): By default, the name for the group of endpoints is user-controller.
 *   We can change it to User (with description) using @Tag annotation.
 * · @Operation(summary = "Read all users", description = "List of All Users"): A summary adds a description of the endpoint, and a description adds extra details.
 * · @ApiResponse(): Adds a single value.
 * · @ApiResponses(value={@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400") }): Adds multiple values
 *
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "User", description = "User CRUD Operations")
public class UserController {
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/users")
    @Operation(summary = "Read all users", description = "List of All Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved users (OK)", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Something went wrong", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })
    public List<User> readAllUsers() {
        return userRepository.findAll();
    }





}