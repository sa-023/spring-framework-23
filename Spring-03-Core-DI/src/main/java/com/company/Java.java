package com.company;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * 🦋 Dependency Injection
 * · Dependency Injection is a fundamental aspect of the Spring framework, where the Spring container "injects" objects
 *   into other objects, or "dependencies".
 * · DI is an application of the IoC principle. The IoC principle implies that the framework controls the application in execution.
 * · Starting with Spring version 4.3, when there is only one constructor in the class, the @Autowired annotation can be omitted.
 *
 * · @Autowired annotation is used by the Spring container to inject the objects into other objects or dependencies.
 * · There are 3 ways in which we can use the @Autowired annotation:
 *   1. Constructor Injection: Injecting the value through the constructor for the class.
 *   2. Field Injection (Not recommended): Injecting the value in the field of the class.
 *   3. Injecting the value through setter.
 *
 */

@Component
@AllArgsConstructor
public class Java {

    OfficeHours officeHours; // OfficeHours dependency

//    // DI-1. Field Injection (Not recommended)
//    @Autowired // Spring Inject (wiring) OfficeHours object to Java object
//    OfficeHours officeHours;


//    // DI-2. Constructor Injection
//    @Autowired
//    public Java(OfficeHours officeHours) {
//        this.officeHours = officeHours;
//    }

    public void getTeachingHours(){
        System.out.println("Weekly teaching hours : " + (20 + officeHours.getHours()));
    }
}
