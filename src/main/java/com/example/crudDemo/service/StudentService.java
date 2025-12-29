package com.example.crudDemo.service;

import com.example.crudDemo.entity.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);

}
