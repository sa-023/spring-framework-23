package com.company;

import com.company.config.ProjectConfig;
import com.company.model.Comment;
import com.company.service.CommentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CompanyApp {
    public static void main(String[] args) {

        Comment comment = new Comment();
        comment.setAuthor("John");
        comment.setText("Spring Framework");

        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class); // container initializes


          // Commenting out below code fragments for testing Eager and Lazy Instantiation.
        
//        CommentService cs1 = context.getBean(CommentService.class);
//        CommentService cs2 = context.getBean(CommentService.class);
//
//        System.out.println(cs1);// Will print memory location of the Object 1
//        System.out.println(cs2);// Will print memory location of the Object 2
//        System.out.println(cs1==cs2);// Will return true if Bean Scope is Singleton (default) and Will return false if Bean Scope is Prototype.



    }
}