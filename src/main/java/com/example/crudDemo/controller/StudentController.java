package com.example.crudDemo.controller;

import com.example.crudDemo.entity.Student;
import com.example.crudDemo.repository.StudentRepo;
import com.example.crudDemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public Student addStudent(@RequestBody Student student){return service.addStudent(student);}

    @GetMapping
    public List<Student> getAllStudents(){return service.getAllStudents();}

    //Read by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){return service.getStudentById(id);}

    //Update
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student){
        return service.updateStudent(id, student);
    }

    //Delete
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        service.deleteStudent(id);
        return "Deleted!";
    }
}
