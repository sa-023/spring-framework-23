package com.company;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCar {

    /*
     * 🦋 Wiring Beans
     * · If any object has some relationship with another object or has a dependency on another object, we have to manage
     *   it by implementing relationships among Beans defined in the Configuration File.
     * · There are 2 ways to establish the relationship between beans:
     *   1. Direct Wiring : The relationship is implemented by directly calling one of the @Bean annotated methods into
     *      another @Bean annotated method. Spring creates relationships between objects.
     *   2. Autowiring : Let the spring provide a value in parameters we define for the @Bean annotated method.
     *
     * 🖍️...
     * · If one object (bean) already exists in the container, spring allows other objects to use the same object.
     * · With direct wiring, we create 2 instances of car object. One instance which Spring creates and adds its context,
     *   and another one created when the person() method make the direct call to the car() method.
     * · With Autowiring, Spring returns directly the car Bean that already exist from the Spring context without creating new one.
     */

    @Bean
    Car car(){
        Car c = new Car();
        c.setMake("Honda");
        return c;
    }

    // Direct Wiring
//    @Bean
//    Person person(){
//        Person p = new Person();
//        p.setName("Mike");
//        p.setCar(car()); // Direct Wiring: Calling the car() method into person method
//        return p;
//    }


    // Auto Wiring
    @Bean
    Person person(Car car){ // we provide car bean as a parameter.
        Person p = new Person();
        p.setName("Mike");
        p.setCar(car); // Autowiring: Passing the car object as a parameter in setCar(car) method
        return p;
    }




}
