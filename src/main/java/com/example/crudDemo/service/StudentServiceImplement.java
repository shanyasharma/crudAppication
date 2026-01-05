package com.example.crudDemo.service;

import com.example.crudDemo.dto.StudentRequestDTO;
import com.example.crudDemo.dto.StudentResponseDTO;
import com.example.crudDemo.entity.Student;
import com.example.crudDemo.exception.StudentNotFoundException;
import com.example.crudDemo.repository.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplement implements StudentService {

    @Autowired
    private StudentRepo repo;

    private StudentResponseDTO mapToResponse(Student student){
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getId());
        dto.setAge(student.getAge());
        dto.setEmail(student.getEmail());
        dto.setName(student.getName());
        return dto;
    }


    private Student mapToEntity(StudentRequestDTO dto){
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        return student;
    }

    @Override
    public StudentResponseDTO addStudent(StudentRequestDTO dto)
    {
        Student saved = repo.save(mapToEntity(dto));
        return mapToResponse(saved);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents()
    {
        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public StudentResponseDTO getStudentById(Long id){
        Student student = repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return mapToResponse(student);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto){
        Student student = repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));


            student.setName(dto.getName());
            student.setEmail(dto.getEmail());
            student.setAge(dto.getAge());
            return mapToResponse(repo.save(student));

    }

    @Override
    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}
