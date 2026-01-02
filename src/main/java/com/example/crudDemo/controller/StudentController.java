package com.example.crudDemo.controller;

import com.example.crudDemo.entity.Student;
import com.example.crudDemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student){
        return new ResponseEntity<>(service.addStudent(student), HttpStatus.CREATED);}

    @GetMapping
    public List<Student> getAllStudents(){return service.getAllStudents();}

    //Read by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){return service.getStudentById(id);}

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student){
        return ResponseEntity.ok(service.updateStudent(id, student));
    }

    //Delete
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        service.deleteStudent(id);
        return "Deleted!";
    }
}
