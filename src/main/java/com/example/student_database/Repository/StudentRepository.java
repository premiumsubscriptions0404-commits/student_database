package com.example.student_database.Repository;

import com.example.student_database.Entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Override
    @EntityGraph(attributePaths = "courses")
    List<Student> findAll();

    @Override
    @EntityGraph(attributePaths = "courses")
    Optional<Student> findById(Integer id);
}
