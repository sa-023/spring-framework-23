package com.company.controller;

import com.company.dto.CourseDTO;
import com.company.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
 * 🦋 Managing the HTTP Response
 * ■ HTTP response is how the backend app uses to send back to the client due to a client’s request. The HTTP response holds data as:
 * · Response Headers : short pieces of data in the response.
 * · Response Body : a larger amount of data that backend needs to send in the response.
 * · Response Status : a short representation of the request’s result.
 * 🖍️...
 * · Request & Response: API Client ← → Rest Controller ← → Service ← → Repository ← → Database.
 *
 * 🦋 Sending Objects as a Response Body
 * · The only thing we need to do to send an object to the client in a response is to make the controller’s action return that object.
 * · When we use an object to model the data transferred between two apps, we name this object a Data Transfer Object (DTO).
 *
 * 🦋 Setting the Response Status and Headers
 * ■ By default, Spring sets some common HTTP statuses:
 * · 200 OK if no exception was thrown on the server-side while processing the request.
 * · 404 Not Found if the requested resource does not exist.
 * · 400 Bad Request if a part of the request could not be matched with the way the server expected the data.
 * · 500 Error on server if an exception was thrown on the server-side for any reason while processing the request.
 * ·❗️However, in some cases, the requirements ask you to configure a custom status.
 * · Spring provides a class called ResponseEntity to specify the response body, the status and headers on the HTTP response.
 *   Ex: ResponseEntity<List<CourseDTO>> class provides the methods to pass the header, body, status, etc. to our API.
 * · We can send data in the HTTP request using request parameters and path variables.
 * · REST endpoints rely on the Spring MVC mechanism, so nothing from the syntax we learned in the MVC changes.
 *
 */

@RestController
@RequestMapping("/courses/api/v2")
public class CourseController_ResponseEntity {
    private final CourseService courseService;
    public CourseController_ResponseEntity(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("Version","Company.V2")
                .header("Operation","Get List")
                .body(courseService.getCourses());
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") Long courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId)); // .ok will return the body and status code 200.
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO course){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Operation","Create")
                .body(courseService.createCourse(course));
    }




}
