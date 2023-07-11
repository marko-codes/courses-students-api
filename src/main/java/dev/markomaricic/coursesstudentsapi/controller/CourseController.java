package dev.markomaricic.coursesstudentsapi.controller;

import dev.markomaricic.coursesstudentsapi.model.Course;
import dev.markomaricic.coursesstudentsapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){

        this.courseService = courseService;

    }

    @PostMapping("/addCourse")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){

        return courseService.addCourse(course);

    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){

        return courseService.getAllCourses();

    }

    @GetMapping("/getAllCoursesWhereFeeLessThan/{fee}")
    public ResponseEntity<List<Course>> getAllCoursesWhereFeeLessThan(@PathVariable double fee) {

        return courseService.getAllCoursesWhereFeeLessThan(fee);

    }

}
