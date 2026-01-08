package com.example.crudDemo.controller;

import com.example.crudDemo.dto.StudentRequestDTO;
import com.example.crudDemo.dto.StudentResponseDTO;
import com.example.crudDemo.service.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        log.info("Student created successfully with id: {}", response.getId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);}

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

    //-------------------Pagination--------------------------------
    @GetMapping("/page")
    public Page<StudentResponseDTO> getStudentsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        log.info("Request to fetch students with pagination: page{}, size{}, sortBy{}, direction{}", page, size, sortBy, direction);
        return service.getStudentsWithPagination(page, size, sortBy, direction);
    }
}
