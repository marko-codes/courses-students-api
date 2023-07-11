package dev.markomaricic.coursesstudentsapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "STUDENT_TBL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    private String department;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "STUDENT_COURSE_TBL",
    joinColumns = {
            @JoinColumn(name = "student_id", referencedColumnName = "id")

    },
    inverseJoinColumns = {
            @JoinColumn(name = "course_id", referencedColumnName = "id")
            }
    )
    @JsonManagedReference
    private Set<Course> courses;

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new HashSet<>();
        }
        courses.add(course);
    }

    public void removeCourse(Course course) {
        if (courses != null) {
            courses.remove(course);
        }
    }

}
