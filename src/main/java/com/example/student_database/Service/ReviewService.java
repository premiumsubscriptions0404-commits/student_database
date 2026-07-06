package com.example.student_database.Service;

import com.example.student_database.Entity.Review;
import com.example.student_database.Repository.ReviewRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReviewService
{
    @Autowired
    private ReviewRespository reviewRepository;

    @Cacheable(value = "reviews", key = "#id")
    public Review findById(int id) {
        return reviewRepository.findById(id).get();
    }

    @Transactional
    @CacheEvict(value = "reviews", allEntries = true)
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Transactional
    @CacheEvict(value = "reviews", allEntries = true)
    public void remove(Review review) {
        reviewRepository.delete(review);
    }
}
