package com.example.crudDemo.service;

import com.example.crudDemo.dto.StudentRequestDTO;
import com.example.crudDemo.dto.StudentResponseDTO;
import com.example.crudDemo.entity.Student;

import java.util.List;

public interface StudentService {
    StudentResponseDTO addStudent(StudentRequestDTO dto);
    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO getStudentById(Long id);

    StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto);
    void deleteStudent(Long id);

}
