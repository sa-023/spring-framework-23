package com.company.bean_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * 🦋 Spring Core
 * · Spring IoC (Inversion of Control) Container is the core of Spring Framework. It creates the objects, configures
 *   and assembles their dependencies, manages their entire life cycle. The Container uses Dependency Injection(DI) to
 *   manage the components that make up the application. It gets the information about the objects from a configuration file(XML)
 *   or Java Code or Java Annotations and Java POJO class. These objects are called Beans. Since the Controlling of Java objects and
 *   their lifecycle is not done by the developers, hence the name Inversion Of Control.
 * · There are 2 types of IoC containers: · BeanFactory  · ApplicationContext
 * · The followings are some of the main features of Spring IoC:
 *   · Creating Object for us, Managing our objects, Helping our application to be configurable, Managing dependencies.
 *
 * 🦋 @Bean Annotation
 * · When the application starts, Spring will look for the following:
 *   1. @Configuration annotated classes that were provided as parameters in the AnnotationConfigApplicationContext(ConfigApp.class, ConfigAny.class).
 *   2. It will go through those classes, find objects with @Bean annotations, create and store the beans in the container.
 *
 * 🖍️...
 * · getBean(); will return the bean object from the container.
 * · @Bean(name = "p1") can be used to name the beans in the @Configuration annotated classes.
 *   Then we can pass the custom bean name as a first parameter to the getBean() method. Ex: container.getBean("p1",PartTimeMentor.class);
 * · @Primary is a Default choice of the Spring. Spring will choose primary if there are multiple bean options, and we do not need to specify a name.
 *
 */
public class CompanyApp {
    public static void main(String[] args) {


        ApplicationContext container = new AnnotationConfigApplicationContext(ConfigApp.class, ConfigAny.class);// Creating container, an instance of the Spring Context

        FullTimeMentor ft = container.getBean(FullTimeMentor.class);
        ft.createAccount();

        //PartTimeMentor pt = container.getBean("p1",PartTimeMentor.class);// First parameter is the name of the instance to which we refer.
        PartTimeMentor pt = container.getBean(PartTimeMentor.class);
        pt.createAccount();

        String str = container.getBean(String.class);
        System.out.println(str);




    }
}
