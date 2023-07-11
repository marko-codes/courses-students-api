package dev.markomaricic.coursesstudentsapi.service;

import dev.markomaricic.coursesstudentsapi.model.Student;
import dev.markomaricic.coursesstudentsapi.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo){
        this.studentRepo = studentRepo;
    }

    @Transactional
    public ResponseEntity<Student> saveStudentAndCourse(Student student){

        try {

            Student savedStudent = studentRepo.save(student);

            return new ResponseEntity<>(savedStudent, HttpStatus.OK);

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
