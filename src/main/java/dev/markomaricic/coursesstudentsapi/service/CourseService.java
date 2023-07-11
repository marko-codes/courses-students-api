package dev.markomaricic.coursesstudentsapi.service;

import dev.markomaricic.coursesstudentsapi.model.Course;
import dev.markomaricic.coursesstudentsapi.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepo courseRepo;

    @Autowired
    public CourseService(CourseRepo courseRepo){
        this.courseRepo = courseRepo;
    }

    @Transactional
    public ResponseEntity<Course> addCourse(Course course){

        try {

            Course addedCourse = courseRepo.save(course);

            return new ResponseEntity<>(addedCourse, HttpStatus.OK);

        }catch (Exception ex){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    public ResponseEntity<List<Course>> getAllCoursesWhereFeeLessThan(double fee){

        try {

            List<Course> courseList = courseRepo.findByFeeLessThan(fee);

            if(courseList.isEmpty()){

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }else {

                return new ResponseEntity<>(courseList, HttpStatus.OK);

            }

        }catch (Exception ex){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    public ResponseEntity<List<Course>> getAllCourses(){

        try {

            List<Course> courseList = courseRepo.findAll();

            if(courseList.isEmpty()){

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }else {

                return new ResponseEntity<>(courseList, HttpStatus.OK);

            }

        }catch (Exception ex){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
