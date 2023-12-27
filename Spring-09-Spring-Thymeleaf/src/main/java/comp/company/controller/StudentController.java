package comp.company.controller;

import comp.company.model.bootstrap.DataGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * 🦋 Template Engine
 * · A template engine is a dependency that allows you to easily get & display in the view variable data that the controller sends.
 * · A Template Engine is a technology that enables the separation of HTML markup and dynamic data.
 * · Example of Java Template Engines: Velocity, Mustache, FreeMarker, Thymeleaf, Groovy, Jade, JSP
 *
 * 🦋 Thymeleaf
 * · Thymeleaf is a Java-based library used to create a web application. It provides a good support serving an HTML5 in web application.
 * · To be able to integrate Thymeleaf with Spring Boot, we need to add the spring-boot-starter-thymeleaf dependency.
 * · Converting HTML file to Thymeleaf Template: We need to add the attribute xmlns:th=“http://www.thymeleaf.org" to <html> tag.
 *   This definition is equivalent to an import in Java. It allows us further to use the prefix “th” to refer to specific features provided by Thymeleaf in the view.
 *
 * 🦋 Thymeleaf tags...
 * · Display the value of model attributes: <h2 th:text="${students.get(0).firstName}"></h2>
 * · Concatenation: <h2 th:text=" 'Hello ' + ${name}"></h2>  or  <h2 th:text="${'Hello ' + name}"></h2>
 * · Absolute URLs: <a th:href="@{https://company.com/}">Company Page</a>
 * · Relative URLs: <a th:href="@{/student/welcome}">Welcome</a>
 * · Adding Parameters to Link (localhost:8080/student/welcome?id=3): <a th:href="@{/student/welcome(id=${students.get(0).firstName})}">Link</a>
 * · Adding Image: <img th:src="@{/images/welcome-38249.png}">
 * · Bringing the external values into view: <title th:text="#{index.page.title}">Title</title>
 * · Fragments are reusable common parts in our pages: <div th:replace="/fragments/index :: footer-menu"></div>
 * · ${attributeKey}, @{url}, #{externalValues}, *{objectField}
 * · Iteration achieved by using th:each: <li th:each="i: ${students}" th:text="${i.firstName}"></li>
 *   Ex1: <ul>
 *           <li th:each = "car : ${cars}" th:text=${car}>Car</li
 *        <ul>
 *   Ex2: <tr th:each="mentor : ${mentors}">
 *            <td th:text="${mentor.firstName}"></td>
 *            <td th:text="${mentor.lastName}"></td>
 *            <td th:text="${mentor.age}"></td>
 *            <td th:text="${mentor.gender}"></td>
 *        </tr>
 * · Adding object fields: <input type="text" id="firstName" th:field="*{firstName}">
 *
 *
 * 🦋 HTTP Methods
 * · GET: The client’s request only retrieves data.
 * · POST: The client’s request sends new data to be added by the server.
 * · PUT: The client’s request changes a data record on the server-side.
 * · PATCH: The client’s request changes only partially a data record on the server-side.
 * · DELETE: The client’s request deletes data on the server-side.
 * 🖍️...
 * · When we work with UI, we only use the GET or POST method.
 *
 * 🦋 Request Mapping Annotations
 * · Before Spring 4.3, Spring had only the @RequestMapping annotation for mapping all the incoming HTTP request URLs to the corresponding controller methods.
 *   Ex: @RequestMapping(value = "/register", method = RequestMethod.GET)
 * · When the HTTP method type is not provided inside the @RequestMapping() annotation, Spring recognizes it as a GET method by default. Ex: @RequestMapping(value = "/register")
 * · Spring 4.3 introduced five new and more specific annotations for each HTTP request type. @GetMapping, @PostMapping, @PutMapping, @DeleteMapping,  @PatchMapping.
 *   Ex: @GetMapping(value = “/users"), @PostMapping(value = “/users")
 *
 * 🔺 @GetMapping
 * · The @GetMapping annotation is a specialized version of @RequestMapping annotation that acts as a shortcut for. Ex: @RequestMapping(method=RequestMethod.GET)
 * · The @GetMapping annotated methods in the @Controller annotated classes handle the HTTP GET requests matched with the URI expression
 * 🔺 @PostMapping
 * · The @PostMapping is specialized version of @RequestMapping annotation that acts as a shortcut for @RequestMapping(method=RequestMethod.POST)
 * · The @PostMapping annotated methods in the @Controller annotated classes handle the HTTP POST requests matched with given URI expression
 *
 */
@Controller
@RequestMapping("/student")
public class StudentController {

//    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @GetMapping("/register")
    public  String register(Model model){

        model.addAttribute("students", DataGenerator.createStudent());
        return "student/register";
    }

//    @RequestMapping("/welcome")
    @GetMapping("/welcome")
    public String info(){
        return "student/welcome";
    }

}
