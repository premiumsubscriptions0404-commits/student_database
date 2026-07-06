package com.example.student_database.Repository;

import com.example.student_database.Entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Integer> {
}
