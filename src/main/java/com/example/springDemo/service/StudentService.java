package com.example.springDemo.service;

import com.example.springDemo.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    public List<Student> findAll(){
        return List.of(new Student(1, "Petrov", 19, "G12"),
                        new Student(2, "Ivanov", 20, "G12"),
                        new Student(3, "Sidorov", 19, "G34"),
                        new Student(4, "Glibenko", 21, "G25"));
    }

    public Optional<Student> findById(int id){
        return Optional.of(new Student(2, "Ivanov", 20, "G12"));
    }

    public void save(Student student){
        System.out.println(student);
    }
}
