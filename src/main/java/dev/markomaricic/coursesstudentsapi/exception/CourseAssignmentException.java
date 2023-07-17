package dev.markomaricic.coursesstudentsapi.exception;

public class CourseAssignmentException extends Exception{

    public CourseAssignmentException(String message){
        super(message);
    }

    public CourseAssignmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
