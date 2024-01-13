package com.company.controller;
import com.company.dto.CourseDTO;
import com.company.service.CourseService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
 *
 * 🦋 HTTP Request Methods
 * · Spring currently supports five types of inbuilt annotations for handling different types of incoming HTTP request methods.
 * · @GetMapping · @PostMapping · @PutMapping · @PatchMapping · @DeleteMapping
 *
 * 🦋 @ResponseBody
 * · This annotation tells the dispatcher servlet that the controller’s action does not return a view name but directly the data sent in the HTTP response.
 * · The @ResponseBody annotation can be applied both at the class level and the method level. When @ResponseBody is applied at the
 *   class level along with the @Controller annotation, another annotation such as @RestController can be used instead.
 * · The @ResponseBody annotation represents the fact that the value returned by the method will form the body of the response.
 *   When the value returned is an object, the object is converted into an appropriate JSON or XML format by HttpMessageConverters.
 *
 * 🦋 @RequestBody
 * · HTTP request has a request body, and we can use it to send data from the client to the server.
 * · @RequestBody annotation binds the request body to method parameters. The process of serialization/deserialization is performed by HttpMessageConverter.
 * · To use the request body, we need to annotate a parameter of the controller’s action with @RequestBody.
 *
 * 🦋 @RestController
 * · @RestController annotation is a combination of @Controller and @ResponseBody.
 * · It is used to instruct Spring that all the controller’s actions are REST endpoints.
 * · @RestController = @Controller + @ResponseBody.
 *
 * 🖍️...
 * · Jackson is doing object conversion to JSON automatically.
 * · /v1 is an API version. We can create a version of the API at the class level or in the application.properties.
 */

@RestController // @Controller + @ResponseBody
@RequestMapping("/courses/api/v1")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }



    @GetMapping
    public List<CourseDTO> getAllCourses(){
        List<CourseDTO> list = courseService.getCourses();
        return list;
    }

    @GetMapping("{id}")
    public CourseDTO getCourseById(@PathVariable("id") Long courseId){
        return courseService.getCourseById(courseId);
    }

    @GetMapping("category/{name}")
    public List<CourseDTO> getCourseByCategory(@PathVariable("name") String category){
        return courseService.getCoursesByCategory(category);

    }

    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO course){
        return courseService.createCourse(course);
    }

    @PutMapping("{id}")
    public void updateCourse(@PathVariable("id") Long courseId,@RequestBody CourseDTO course){
        courseService.updateCourse(courseId,course);
    }

    @DeleteMapping("{id}")
    public void deleteCourseById(@PathVariable("id") Long courseId){
        courseService.deleteCourseById(courseId);
    }

    @DeleteMapping
    public void deleteCourses(){
        courseService.deleteCourses();
    }




}
