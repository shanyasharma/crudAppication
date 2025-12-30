package com.example.crudDemo.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id){
        super("Student Not found with id: "+id);
    }

}
