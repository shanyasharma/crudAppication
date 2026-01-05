package com.example.crudDemo.controller;

import com.example.crudDemo.dto.StudentRequestDTO;
import com.example.crudDemo.dto.StudentResponseDTO;
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
    public ResponseEntity<StudentResponseDTO> addStudent(@Valid @RequestBody StudentRequestDTO dto){
        return new ResponseEntity<>(service.addStudent(dto), HttpStatus.CREATED);}

    @GetMapping
    public List<StudentResponseDTO> getAllStudents(){
        return service.getAllStudents();
    }

    //Read by ID
    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id){
        return service.getStudentById(id);}

    //Update
    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO dto){
        return service.updateStudent(id, dto);
    }

    //Delete
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        service.deleteStudent(id);
        return "Deleted!";
    }
}
