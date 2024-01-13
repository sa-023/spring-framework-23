package com.company.controller;
import com.company.entity.ResponseWrapper;
import com.company.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 * 🦋 CUSTOMIZING THE JSON OUTPUT
 * · Create a custom class called ResponseWrapper in the entity package and use that class in your controller methods.
 */
@RestController
@RequestMapping("/courses/api/v3")
public class CourseController_ResponseWrapper {
    private final CourseService courseService;
    public CourseController_ResponseWrapper(CourseService courseService) {
        this.courseService = courseService;
    }



    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllCourses(){ // ResponseWrapper is a custom object.
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("Version","Company.V3")
                .body(new ResponseWrapper("successfully retrieved",courseService.getCourses()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWrapper> getCourseById(@PathVariable("id") Long courseId){
        return ResponseEntity.ok(new ResponseWrapper("course:" + courseId + "retrieved",courseService.getCourseById(courseId)));
    }
}
