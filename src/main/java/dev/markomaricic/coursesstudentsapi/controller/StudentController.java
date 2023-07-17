package dev.markomaricic.coursesstudentsapi.controller;

import dev.markomaricic.coursesstudentsapi.exception.CourseAssignmentException;
import dev.markomaricic.coursesstudentsapi.model.Student;
import dev.markomaricic.coursesstudentsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){

        this.studentService = studentService;

    }

    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){

        return studentService.addStudent(student);

    }

    @PostMapping("/assignCourseToStudent/{sid}/{cid}")
    public List<ResponseEntity<Student>> assignCourseToStudent(@PathVariable("sid") Long studentId, @PathVariable("cid") List<Long> coursesId) throws CourseAssignmentException {

        return studentService.assignCourseToStudent(studentId, coursesId);

    }

    @DeleteMapping("/removeStudent/{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable("id") Long id){

        return studentService.removeStudent(id);

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
