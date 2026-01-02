package com.example.crudDemo.entity;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message= "Email cannot be empty")
    private String email;

    @Min(value = 1, message = "Age must be greater than 0")
    private int age;

    public Student(){}

    public Student(String name, String email, int age){
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Long getId() {return id;}


    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
}
