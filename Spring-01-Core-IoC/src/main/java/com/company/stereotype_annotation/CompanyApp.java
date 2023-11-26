package com.company.stereotype_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CompanyApp {
    public static void main(String[] args) {

        /*
         * 🦋 Stereotype Annotations
         * · When the application starts, Spring will look for the following:
         *   1. @Configuration annotated classes that were provided as parameters in the AnnotationConfigApplicationContext(ConfigCourse.class).
         *   2. @ComponentScan annotation in the configuration class.
         *   3. @Component annotated classes belong to the package, then we will create the beans.
         *
         * 🖍️...
         * · @Configuration: When the application starts Spring will check @Configuration annotated classes first.
         * · @ComponentScan: Finds and scans all the classes annotated with @Component annotations inside the package.
         * · @ComponentScan(basePackages = "com.company"): Scan all the @Component belong to "com.company" package.
         * · @Component: Beans will be created from @Component annotated classes.
         * · Stereotype annotations (@Component, @ComponentScan) cannot be used with classes that are not ours and cannot be changed.
         *   For ex: We can't (add @Component annotation) edit String, Integer, etc. classes, so we always follow the @Bean annotation with these classes.
         *
         */


        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigCourse.class);
        context.getBean(Java.class).getTeachingHours();

    }
}
