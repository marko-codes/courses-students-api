package dev.markomaricic.coursesstudentsapi.service;

import dev.markomaricic.coursesstudentsapi.exception.CourseAssignmentException;
import dev.markomaricic.coursesstudentsapi.model.Course;
import dev.markomaricic.coursesstudentsapi.model.Student;
import dev.markomaricic.coursesstudentsapi.repo.CourseRepo;
import dev.markomaricic.coursesstudentsapi.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class StudentService {

    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo, CourseRepo courseRepo){
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @Transactional
    public List<ResponseEntity<Student>> assignCourseToStudent(Long studentId, List<Long> coursesId) throws CourseAssignmentException {
        List<ResponseEntity<Student>> responseEntities = new ArrayList<>();

        try {
            Optional<Student> optionalStudent = studentRepo.findById(studentId);

            if (optionalStudent.isEmpty()) {
                responseEntities.add(new ResponseEntity<>(HttpStatus.NOT_FOUND));
                return responseEntities;
            }

            Student student = optionalStudent.get();
            List<Course> assignedCourses = new ArrayList<>();

            for (Long courseId : coursesId) {
                Optional<Course> optionalCourse = courseRepo.findById(courseId);


                if (optionalCourse.isEmpty()) {

                    responseEntities.add(new ResponseEntity<>(HttpStatus.NOT_FOUND));

                    throw new CourseAssignmentException("Course with ID " + courseId + " not found. Rolling back.");

                }else if(student.getCourses().contains(optionalCourse.get())){

                    responseEntities.add(new ResponseEntity<>(HttpStatus.CONFLICT));

                    throw new CourseAssignmentException("Course with ID " + courseId + " is already assigned. Rolling back.");
                }

                Course course = optionalCourse.get();
                student.addCourse(course);
                assignedCourses.add(course);
            }

            studentRepo.save(student);

            for (Course course : assignedCourses) {
                responseEntities.add(new ResponseEntity<>(student, HttpStatus.OK));
            }

            return responseEntities;

        } catch (CourseAssignmentException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            responseEntities.add(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            throw new CourseAssignmentException("Failed to assign courses. Rolling back.", ex);
        }
    }


    @Transactional
    public ResponseEntity<Student> addStudent(Student student){

        try {

            Student addedStudent = studentRepo.save(student);

            return new ResponseEntity<>(addedStudent, HttpStatus.CREATED);

        }catch (Exception ex){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    public ResponseEntity<Student> removeStudent(Long id){

        try{

            Optional<Student> student = studentRepo.findById(id);

            if(student.isPresent()){
                student.get().getCourses().clear();
                studentRepo.delete(student.get());

                return new ResponseEntity<>(student.get(), HttpStatus.OK);
            }else {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }


        }catch (Exception ex){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    public ResponseEntity<List<Student>> getAllStudents(){

        try {

            List<Student> studentList = studentRepo.findAll();

            if(studentList.isEmpty()){

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }else {
                return new ResponseEntity<>(studentList, HttpStatus.OK);
            }


        }catch (Exception ex){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    public ResponseEntity<Optional<Student>> findStudentById(@PathVariable Long studentId){

        try {

            Optional<Student> student = studentRepo.findById(studentId);

            if(student.isPresent()){

                return new ResponseEntity<>(student, HttpStatus.OK);

            }else {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }


        }catch (Exception ex){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    public ResponseEntity<List<Student>> getStudentByName(String name){

        try {

            List<Student> studentList = studentRepo.findByNameContaining(name);

            if(studentList.isEmpty()){

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }else {

                return new ResponseEntity<>(studentList, HttpStatus.OK);

            }
        }catch (Exception ex){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
