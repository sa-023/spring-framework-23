package com.company;

import com.company.model.Comment;
import com.company.service.CommentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/*
 * 🦋 Spring Boot and key features:
 * · Standalone applications: Spring Boot helps create apps that aren't tied to a specific platform and that can run
 *   locally on a device without an internet connection or other installed services to be functional.
 * · Embedded servers: Applications run on the server. Spring Boot comes with built-in HTTP (Hypertext Transfer Protocol) servers like Tomcat and Jetty.
 * · Opinionated approach: Spring Boot simplifies build configurations by providing opinionated starter dependencies.
 * · Autoconfiguration: Spring Boot automatically configures Spring and other third-party libraries whenever possible.
 *
 * 🖍️...
 * · Spring Boot reduces the need to write a lot of configuration and boilerplate code. Instead, developers can focus on building business logic.
 * · Spring Boot uses an opinionated approach to adding and configuring starter dependencies, based on the needs of your project.
 *   Following its own judgment, Spring Boot chooses which packages to install and which default values to use, rather than
 *   requiring you to make all those decisions yourself and set up everything manually.
 *   For example, the ‘Spring Web’ starter dependency allows you to build Spring-based web applications with minimal configuration
 *   by adding all the necessary dependencies—such as the Apache Tomcat web server—to your project. ‘Spring Security’ is another
 *   popular starter dependency that automatically adds authentication and access-control features to your application.
 *
 * ❗️@SpringBootApplication consists of three annotations:
 * 1. @SpringBootConfiguration: Designates this class as a configuration class. You can add Java-based Spring framework
 *    configuration to this class if you need to. This annotation is a specialized form of the @Configuration annotation.
 * 2. @EnableAutoConfiguration: Enables Spring Boot automatic configuration. It tells Spring Boot to automatically
 *    configure any components that it thinks we will need.
 * 3. @ComponentScan: Enables component scanning. This lets you declare other classes with annotations like @Component
 *    to have Spring automatically discover and register them as components in the Spring application context.
 *
 * · main() method: calls a static run method on the SpringApplication class, which performs the actual bootstrapping
 *   of the application, creating the Spring application context. The two parameters passed to the run() method are a configuration class and the command-line arguments.
 */
@SpringBootApplication
public class CompanyApplication {

    public static void main(String[] args) {


        Comment comment = new Comment();
        comment.setAuthor("John");
        comment.setText("Spring Framework");

        ApplicationContext context = SpringApplication.run(CompanyApplication.class, args);

        CommentService cs = context.getBean(CommentService.class);
        cs.publishComment(comment);




    }

}
