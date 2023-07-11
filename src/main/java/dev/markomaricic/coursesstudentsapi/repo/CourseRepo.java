package dev.markomaricic.coursesstudentsapi.repo;

import dev.markomaricic.coursesstudentsapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {

    List<Course> findByFeeLessThan(double fee);

}
