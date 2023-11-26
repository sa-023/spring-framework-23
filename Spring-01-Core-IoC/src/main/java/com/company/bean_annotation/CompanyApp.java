package com.company.bean_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CompanyApp {
    public static void main(String[] args) {

        /*
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
         */


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
