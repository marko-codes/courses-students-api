package dev.markomaricic.coursesstudentsapi.repo;

import dev.markomaricic.coursesstudentsapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {

    List<Student> findByNameContaining(String name);

}
