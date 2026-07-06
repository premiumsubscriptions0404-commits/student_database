package com.example.student_database.Service;
import java.util.List;

import com.example.student_database.Entity.Course;
import com.example.student_database.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CourseService
{
    @Autowired
    private CourseRepository courseRepository;

    @Cacheable(value = "courses", key = "'all'")
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Cacheable(value = "courses", key = "#id")
    public Course findById(int id) {
        return courseRepository.findById(id).get();
    }

    @Transactional
    @CacheEvict(value = "courses", allEntries = true)
    public Course save(Course course) {
        return courseRepository.save(course);
    }
}