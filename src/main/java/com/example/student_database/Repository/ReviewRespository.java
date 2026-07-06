package com.example.student_database.Repository;

import com.example.student_database.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRespository extends JpaRepository<Review,Integer> {
}
