package com.example.crudDemo.controller;

import com.example.crudDemo.dto.StudentRequestDTO;
import com.example.crudDemo.dto.StudentResponseDTO;
import com.example.crudDemo.entity.Student;
import com.example.crudDemo.service.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> addStudent(@Valid @RequestBody StudentRequestDTO dto){
        log.info("Request to create student with emai: {}", dto.getEmail());
        StudentResponseDTO response = service.addStudent(dto);
        log.info("Student created succesfully with id: {}", response.getId());

        return new ResponseEntity<>(service.addStudent(dto), HttpStatus.CREATED);}

    @GetMapping
    public List<StudentResponseDTO> getAllStudents(){
        log.info("Request to fetch all students");
        return service.getAllStudents();
    }

    //Read by ID
    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id){
        log.info("Request to fetch students with id: {}", id);
        return service.getStudentById(id);
    }

    //Update
    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO dto){
        log.info("Request to update students with id: {}", id);
        return service.updateStudent(id, dto);
    }

    //Delete
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        log.warn("Request to delete student with id: {}", id);
        service.deleteStudent(id);
        return "Deleted!";
    }
}
