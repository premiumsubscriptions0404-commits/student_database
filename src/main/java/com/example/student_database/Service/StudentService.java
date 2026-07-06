package com.example.student_database.Service;
import java.util.List;

import com.example.student_database.Entity.Student;
import com.example.student_database.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StudentService
{
    @Autowired
    private StudentRepository studentRepository;

    @Cacheable(value = "students", key = "'all'")
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Cacheable(value = "students", key = "#id")
    public Student findById(int id) {
        return studentRepository.findById(id).get();
    }

    @Transactional
    @CacheEvict(value = "students", allEntries = true)
    public Student save(Student student) {
        return studentRepository.save(student);
    }
}