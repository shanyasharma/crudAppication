package com.example.crudDemo.service;

import com.example.crudDemo.entity.Student;
import com.example.crudDemo.repository.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplement implements StudentService {

    @Autowired
    private StudentRepo repo;

    @Override
    public Student addStudent(Student student){
        return repo.save(student);
    }

    @Override
    public List<Student> getAllStudents(){
        return repo.findAll();
    }

    @Override
    public Student getStudentById(Long id){
        return repo.findById(id).orElse(null);
    }

    @Override
    public Student updateStudent(Long id, Student student){
        Student existing = repo.findById(id).orElse(null);

        if(existing != null){
            existing.setName(student.getName());
            existing.setEmail(student.getEmail());
            existing.setAge(student.getAge());
            return repo.save(existing);
        }
        return null;
    }

    @Override
    public void deleteStudent(Long id){
        repo.deleteById(id);
    }
}
