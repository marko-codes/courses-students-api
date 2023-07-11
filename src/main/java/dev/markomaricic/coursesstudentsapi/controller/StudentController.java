package dev.markomaricic.coursesstudentsapi.controller;

import dev.markomaricic.coursesstudentsapi.model.Student;
import dev.markomaricic.coursesstudentsapi.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){

        this.studentService = studentService;

    }

    @PostMapping("/addStudent")
    public ResponseEntity<Student> saveStudentWithCourse(@RequestBody Student student){

        return studentService.saveStudentAndCourse(student);

    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){

        return studentService.getAllStudents();

    }

    @GetMapping("/findById/{studentId}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Long studentId){

        return studentService.findStudentById(studentId);

    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<Student>> getStudentByName(@PathVariable String name){

        return studentService.getStudentByName(name);

    }

}
