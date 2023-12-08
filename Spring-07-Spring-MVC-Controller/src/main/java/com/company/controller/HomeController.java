package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 🦋 Servlet Container
 * · A servlet is a Java Object which directly interacts with the Servlet Container.
 * · Servlet Container is a translator of the HTTP messages for our Java app.
 * · Servlet Container has a context of servlet instances it controls, just as Spring does with its beans.
 * · Tomcat is called a ‘Servlet Container’.
 * · We do not create servlet instances. Servlet is the entry point to our app’s logic. It is the component the Servlet Container (Tomcat) directly interacts with.
 *
 * 🦋 Spring MVC (Model, View, Controller) Architecture:
 * 1. The client makes an HTTP request
 * 2. Tomcat receive the client’s HTTP request, and it calls a servlet component which also known as Dispatcher Servlet or Front Controller.
 * 3. Dispatcher Servlet’s responsibility is to manage the request further inside the Spring App. It has to find what controller
 *    action to call for the request and what to send back in response to the client.
 * 4. To find which controller action to call for the request, the Dispatcher Servlet delegates to a component named Handler Mapping.
 *    The HandlerMapping component parses a Request and finds a Handler that handles the Request, which is generally understood as a method in the Controller.
 * 5. Dispatcher Servlet calls that specific controller action after getting response from HandlerMapping.
 *    Controller returns to the dispatcher server the HTML page, which we refer to as VIEW.
 * 6. Dispatcher servlet delegates the responsibility of getting the view content to a component named View Resolver.
 * 7. Dispatcher Servlet returns in the HTTP response the rendered view.
 * 🖍️...
 * · Tomcat ←→ 1. DispatcherServlet ←→ Handler Mapping ▶︎ 2. DispatcherServlet ←→ Controller ▶︎ 3. DispatcherServlet ←→ View Resolver
 * · DispatcherServlet is a that receives all the HTTP requests and delegates them to controller classes.
 *   Spring Boot provides the spring-boot-starter-web library for developing web applications using Spring MVC. One of the main features of Spring Boot is autoconfiguration.
 *   Spring Boot autoconfiguration registers and configures the DispatcherServlet automatically. Therefore, we don’t need to register the DispatcherServlet manually.
 * · MVC stands for Model-view-controller:
 *   1. Model: It works a container that contains the data of the application.
 *   2. View: It is an HTML page, which we refer to as a view.
 *   3. Controller: The controller actions and methods associated with specific HTTP requests.
 *
 * 🔺 @Controller
 * · @Controller stereotype annotation indicates that the annotated class is controller.
 * · The controller actions are methods associated with specific HTTP requests.
 * · It is specialized form of @Component and is autodetected through classpath scanning. (@Controller, @Service, @Repository specialized form of @Component annotation.)
 *   Instead of using @Component on a controller class in Spring MVC, we use @Controller, which is more readable and appropriate.
 * · It is typically used in combination with annotated handler methods based on the @RequestMapping annotation.
 *
 * 🔺 @RequestMapping
 * · @RequestMapping annotation maps HTTP requests to handler methods of MVC and REST controllers.
 * · It can be applied to class level and/or method level.
 *
 * 🖍️...
 * · Ex: When we send a request to http://localhost:8080/home, the dispatcher server will look for all the classes that are
 *   annotated with @Controller, then scan the classes to find a method with the provided annotation and endpoint @RequestMapping("/home")
 * · The MVC View page can be any HTML or JSP file.
 *
 *
 *
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(){
        return "home.html";
    }

    @RequestMapping("/welcome")
    public String home2(){
        return "welcome.html";
    }

    @RequestMapping // If we don't provide an endpoint with @RequestMapping, it takes the default option @RequestMapping("/"), which is one slash
    public String home3(){
        return "welcome.html";
    }

}
