package com.example.crudDemo.service;

import com.example.crudDemo.dto.StudentRequestDTO;
import com.example.crudDemo.dto.StudentResponseDTO;
import com.example.crudDemo.entity.Student;
import com.example.crudDemo.exception.StudentNotFoundException;
import com.example.crudDemo.repository.StudentRepo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
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
        log.info("Adding new student with email: {}", dto.getEmail());
        Student saved = repo.save(mapToEntity(dto));
        log.info("Student saved succesfully with id: {}", saved.getId());
        return mapToResponse(saved);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents()
    {
        log.info("Fetch all students");
        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public StudentResponseDTO getStudentById(Long id){
        log.info("Fetch student with id: {}", id);
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
    public void deleteStudent(Long id)
    {
        log.warn("Deleting student with id: {}", id);
        repo.deleteById(id);
    }
}
