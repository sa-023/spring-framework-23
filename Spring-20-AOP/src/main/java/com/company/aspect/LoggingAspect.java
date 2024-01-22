package com.company.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import java.util.List;
/*
 * 🦋 To implement AOP
 * · We should include the spring-boot-starter-aop dependency in pom.xml to use Spring AOP features.
 * · Create a configuration class and annotate it with the @Aspect annotation.
 *
 * 🦋 Concern
 * · A Concern is a term that refers to a part of the system divided on the basis of the functionality.
 * · There are two types of concerns:
 * 1. The concerns representing single and specific functionality for primary requirements are known as core concerns.
 *    OR Primary functionality of the system is known as core concerns. Ex: Business logic
 * 2. The concerns representing functionalities for secondary requirements are referred to as crosscutting concerns or system-wide concerns.
 *    OR The crosscutting concern is a concern which is applicable throughout the application, and it affects the entire application.
 *    Ex: logging, security and data transfer are the concerns which are needed in almost every module of an application, hence they are cross-cutting concerns.
 *
 * 🦋 Spring AOP
 * · Application logic can be broken into 2 distinct areas, core business logic and cross-cutting concerns. Business logic is code
 *   written to satisfy a functional requirement, while a cross-cutting concern is ‘utility’ logic that is agnostic to a specific
 *   business process but required by many parts of the application. Examples include logging, transaction management, performance
 *   monitoring and security. While none of these address a functional requirement, they remain fundamental parts of the application.
 * · Cross cutting concerns present 2 main challenges.
 *   1. They tend to be ‘scattered’ across the application which can lead to considerable duplicate code. Logging or performance monitoring is a good example.
 *   2. They become tangled with application business logic and result in code that is difficult to maintain because there is no clear Separation of Concerns.
 * · Aspect Oriented Programming (AOP) aims to address these challenges by providing a means of modularising application logic,
 *   so that each module addresses a distinct concern. Take performance monitoring as an example – rather than have performance
 *   monitoring logic littered across the application, AOP provides a means of modularising this logic, and applying it to various
 *   parts of the application at runtime. This provides a clear Separation of Concerns as performance monitoring logic is no longer
 *   tangled with business logic throughout the application.
 * 🖍️...
 * · AOP provides a way to dynamically add the crosscutting concern before, after or around the actual logic using simple pluggable configurations.
 * · AOP separates business logic from secondary logic.
 *
 * 🦋 AOP Use Cases
 * · Most Common: Logging, security.
 * · Audit Logging: Who, What, When, Where.
 * · Exception Handling: Log exception and notify DevOps team via email.
 * · API Management: How many times has a method been called user? Analytics: What are peak times? What is the average load?
 *
 * 🦋 Core AOP Concepts
 * 🔺 Aspect: The module of the code for cross-cutting concerns (logging, security). We use aspect classes for related logic. Aspect class is not bean.
 *    · An aspect can be defined using the @Aspect annotation, and it is not a stereotype annotation.
 *    · Using @Aspect, you will tell Spring that the class implements the definition of an aspect, but Spring won’t also create a bean for this class.
 * 🔺 Advice (When): Logic that is invoked at a specific point in a program's execution. What action is taken and when should it be applied? @Before, @After etc.
 *    · @Before: Advice runs before the method.
 *    · @After: Advice runs after the method (finally).
 *    · @AfterReturning: Advice runs after method (success execution).
 *    · @AfterThrowing: Advice runs after method (if exception is thrown).
 *    · @Around: Advice in SpringAOP that runs around a matched JoinPoint’s execution.
 *      · Around advice is declared by using the @Around annotation. It contains code which is executed before and after the matched method(JoinPoint).
 *      · The first parameter of the advice method must be of type ProceedingJoinPoint.
 *      · Within the body of advice, calling proceed() on the ProceedingJoinPoint causes the underlying method to execute.
 *      · In most of the case, it is important to return a value from the advice method as the matched JoinPoint can have a return type.
 * 🔺 Join Point (What): A specific point in a programs execution where advice is applied. Note that advice can be applied before and after a Join Point.
 *    · Ex: Method execution, constructor call etc.
 * 🔺 Pointcut (Where): A predicate that matches join points. Advice is associated with a pointcut expression and runs at any join point matched by the pointcut.
 *    · Pointcut Designators:
 *      1. execution: For matching method execution join points. This is the most widely used PCD(Pointcut Designators). Ex: @Pointcut(POINTCUT_EXPRESSION).
 *      2. within: For matching methods of classes within certain types e.g. classes within a package.
 *         @within: For matching to join points within types (target object class) that have the given annotation. @within is used for class-level annotations.
 *      3. this: For matching to join points (the execution of methods) where the bean reference (Spring AOP proxy) is an instance of the given type.
 *      4. target: For matching with the target object of the specific instance type.
 *      5. args: For matching with methods where its arguments are of a specific type.
 *      6. @args: For matching with methods where its arguments are annotated with a specific annotation.
 *      7. @annotation: For matching to join points where the subject (method) of the Join-point has the given annotation. @annotation is used for method-level annotations.
 *      8. bean(idOrNameOfBean): this PCD lets you limit the matching of join points to a particular named Spring bean or to a set of named Spring beans.
 *
 * 🔎 Combining Pointcut Expression
 * · We can combine multiple pointcut expressions by using && -> And , || -> Or, ! -> Not.
 *
 * 📌 Ex: execution(* services.*.*(..))
 * 1. execution()          : It is equivalent to saying, “When the method is called..."
 * 2. * services.*.*(..)   : The parameter given to execution() specifies which methods whose execution is intercepted.
 * 3. *                    : First * means the intercepted method may have any returned type.
 * 4. services             : This means the intercepted method must be in the service packages.
 * 5. *                    : Seconds * means the intercepted method can be in any class. All the methods from all the classes are intercepted.
 * 6. *                    : Third * means the intercepted method can have any name. All the methods are intercepted.
 * 7. (..)                 : Means the intercepted method can have any parameters.
 *
 */
@Aspect // Will prevent bean creation, but it will provide pluggable configuration (AOP).
@Configuration
public class LoggingAspect {
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);



//    // 1.
//    @Pointcut("execution(* com.company.controller.CourseController.*(..))") // All the methods inside the specified controller class.
//    private void pointcut(){}
//    @Before("pointcut()") // We pass the pointcut method name inside the Advice @Before annotation.
//    public void log(){
//        logger.info("Logger info ------");
//    }
//
//
//   // 2.
//    @Before("execution(* com.company.controller.CourseController.*(..))") // We can create one method and pass the pointcut directly into the @Advice annotation.
//    public void beforeAdvice(){
//        logger.info("Logger info ------");
//    }


//    // 3.
//    @Pointcut("execution(* com.company.repository.CourseRepository.findById(*))") // Method with any return type and accepts one parameter with any data type.
//    private void anyProductRepositoryFindById(){}
//    @Before("anyProductRepositoryFindById()")
//    public void beforeCourseRepoOperation(JoinPoint joinPoint){ // JoinPoint is to get specific information about the method.
//        logger.info("Before (findById) : -> Method: {} - Arguments: {} - Target: {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget()); // Curly braces are mandatory.
//        /*
//         * 🖍️...Output will look like:
//         * · Before (findById) : -> Method: execution(Optional org.springframework.data.repository.CrudRepository.findById(Object))
//         * · - Arguments: [1]
//         * · - Target: org.springframework.data.jpa.repository.support.SimpleJpaRepository@86ff37f
//         */
//    }


//    // 4.
//    @Pointcut("within(com.company.controller..*)") // Match with any joinpoint within controller package and its sub-packages.
//    // @Pointcut("within(com.company.controller.*)") // Match with any joinpoint within controller package.
//    private void anyControllerOperation(){}
//    @Pointcut("@within(org.springframework.stereotype.Service)") // @within is used for class-level annotations.
//    // Match with any join point (method) execution where the declared type of the target object (the class) has a @Service annotation.
//    private void anyServiceOperation(){
//    }
//    @Before("anyControllerOperation() || anyServiceOperation()") // We can use "within" when we combine two pointcuts.
//    public void beforeControllerAdvice(JoinPoint joinPoint){
//        logger.info("Before () -> Method : {} - Arguments : {} - Target: {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
//    }



//    // 5.
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)") // @annotation is used for method-level annotations.
//    private void anyDeleteCourseOperation(){}
//    @Before("anyDeleteCourseOperation()")
//    public void beforeControllerAdvice(JoinPoint joinPoint){
//        logger.info("Before -> Method : {} - Arguments : {} - Target: {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
//    }





//    // 6.
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)") // @annotation is used for method-level annotations.
//    private void anyGetCourseOperation(){}
//    @AfterReturning(pointcut = "anyGetCourseOperation()", returning = "result") // It will return the key and value.
//    public void afterReturningControllerAdvice(JoinPoint joinPoint, Object result){
//        logger.info("After returning -> Method: {} - result: {}", joinPoint.getSignature().toShortString(),result.toString());
//    }
//    @AfterReturning(pointcut = "anyGetCourseOperation()", returning = "result")
//    public void afterReturningControllerAdvice(JoinPoint joinPoint, List<Object> result){
//        logger.info("After returning(List) -> Method: {} - result: {}", joinPoint.getSignature().toShortString(),result.toString());
//    }
//    @AfterThrowing(pointcut = "anyGetCourseOperation()", throwing = "exception") // Output: After Throwing -> Method: CourseController.getCourseById(..) - Exception: No value present
//    public void afterThrowingControllerAdvice(JoinPoint joinPoint, RuntimeException exception){
//        logger.info("After Throwing -> Method: {} - Exception: {}", joinPoint.getSignature().toShortString(), exception.getMessage());
//    }
//    @After("anyGetCourseOperation()") // Output for getCourseById() method: After finally -> Method : CourseController.getCourseById(..)
//    public void afterControllerAdvice(JoinPoint joinPoint) {
//        logger.info("After finally -> Method : {}", joinPoint.getSignature().toShortString());
//    }



    // 7.
    @Pointcut("@annotation(com.company.annotation.Loggable)") // @annotation will return log info for methods that have been annotated with @Loggable annotation.
    private void anyLoggableMethodOperation(){}
    @Around("anyLoggableMethodOperation()") // @Around will run before and after the method.
    public Object anyLoggableMethodOperationAdvice(ProceedingJoinPoint proceedingJoinPoint){
        logger.info("Before () -> Method : {} - Parameters: {}", proceedingJoinPoint.getSignature().toShortString(), proceedingJoinPoint.getArgs());
        Object results = null;
        try {
            results = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        logger.info("After -> Method: {} - Results: {}", proceedingJoinPoint.getSignature().toShortString(),results.toString());
        return results;
        /*
         * 🖍️...
         * · @Around: The first parameter is of type ProceedingJoinPoint. Code should contain proceed() on the ProceedingJoinPoint, and it causes the
         *   underlying lines of code to execute. It contains the code that has to be executed before and after when the method is matched with the pointcut
         */
    }







}
