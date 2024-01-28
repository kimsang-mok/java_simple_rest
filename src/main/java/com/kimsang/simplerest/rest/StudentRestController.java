package com.kimsang.simplerest.rest;

import com.kimsang.simplerest.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // Load student data ... only once
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Kimsang", "Mok"));
        theStudents.add(new Student("Kimheng", "Mok"));
        theStudents.add(new Student("Ratana", "Mok"));
    }

    // define endpoint that return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    // path variable is like a parameter
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {
        // check the student_id against list size
        if (studentId >= theStudents.size() || studentId < 0) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }
}
