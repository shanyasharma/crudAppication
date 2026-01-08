package com.example.crudDemo.service;

import com.example.crudDemo.dto.StudentRequestDTO;
import com.example.crudDemo.dto.StudentResponseDTO;
import org.springframework.data.domain.Page;


import java.util.List;

public interface StudentService {
    StudentResponseDTO addStudent(StudentRequestDTO dto);
    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO getStudentById(Long id);

    StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto);
    void deleteStudent(Long id);

    Page<StudentResponseDTO> getStudentsWithPagination(
            int page,
            int size,
            String sortBy,
            String direction
    );

}
