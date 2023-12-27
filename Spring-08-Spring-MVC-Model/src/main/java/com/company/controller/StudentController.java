package com.company.controller;

import com.company.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 🦋 Spring MVC Flow:
 * · The client sends an HTTP request to the web server.
 * · The dispatcher servlet uses the handler mapping to find out what controller action to call.
 * · The dispatcher servlet calls the controller’s action.
 * · After executing the action associated with the HTTP request, the controller returns the view name (and data to view)
 *   that the dispatcher servlet needs to render into the HTTP response.
 * · The response sent back to the client.
 *
 * 🦋 Model
 * · The Model works as a container that contains the data of the application.
 * · In controller, any data can be added (strings, objects, from database, etc...)
 * · View page can access data from model.
 *
 * 🦋 Template Engine
 * · A template engine is a dependency that allows you to easily get and display in the view variable data that the controller sends.
 * · Spring supports many view templates: · JSP (Java Server Pages) · Thymeleaf · Groovy · Jade
 *
 * ■ Model Interface:
 * · Model addAllAttributes(Collection<?> attributeValues) - It copies all the attributes in the provided collection into this Map.
 * · Model addAllAttributes(Map<String, ?> attributes) - It copies all the attributes in the provided Map into this Map.
 * · Model addAttribute(Object attributeValue) - It adds the given attribute to this Map through a generated name
 * · Boolean containsAttribute(String attributeName) - It searches whether the model contains the attribute of the given name.
 * · Model mergeAttributes(Map<String, ?> attributes) - It copies all the attributes in the given Map into this Map, with the existing objects of the same name.
 *
 * 🖍️...
 * · To directly integrate the data(Model) from the controller into the view (HTML), we use a Thymeleaf template engine.
 * · To use Thymeleaf: 1. Dependency needs to be added to the pom.xml file. 2. The line "xmlns:th="http://www.thymeleaf.org" needs to be added to the HTML file.
 *   It allows us further to use the prefix “th” to refer to specific features provided by thymeleaf in the view.
 *
 * · src/main/resources/static -> If the view is static, Spring will look into the static package.
 * · src/main/resources/templates -> If the view is dynamic, Spring will look into the templates package.
 *
 */

@Controller
@RequestMapping("/student") // Class-level endpoint will concatenate with method-level endpoints
public class StudentController {

    @RequestMapping("/welcome") // http://localhost:8080/student/welcome
    public String homePage(Model model){

        model.addAttribute("name", "Company");
        model.addAttribute("course", "MVC");

        String subject = "Collections";
        model.addAttribute("subject", subject );

        int studentId= new Random().nextInt();
        model.addAttribute("id", studentId);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(4);
        numbers.add(5);
        numbers.add(7);
        numbers.add(10);
        model.addAttribute("numbers",numbers);

        Student student = new Student(1,"Diana","Smith");
        model.addAttribute("student",student);

        return "student/welcome"; // It returns a thymeleaf template because of that we don't use .html

    }



}
