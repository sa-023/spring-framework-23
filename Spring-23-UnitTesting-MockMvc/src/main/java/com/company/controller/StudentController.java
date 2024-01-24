package com.company.controller;
import com.company.entity.Student;
import com.company.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/student")
    public Student getStudent() {
        return new Student("Mike", "Smith", 20);
    }

    @GetMapping("/service/student")
    public List<Student> getStudent_Service() {
        return studentService.getStudent();
    }






}
