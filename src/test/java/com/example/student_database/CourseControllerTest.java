package com.example.student_database;

import com.example.student_database.Controller.CourseController;
import com.example.student_database.Entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {

    @InjectMocks
    private  CourseController courseController;

    @Mock
    private Model model;


    void setup()
    {

    }

    @Test
    void addtest()
    {
        //added test case
        String name=courseController.add(model);
        assertEquals("course-form",name);
        verify(model).addAttribute(eq("theCourse"),any(Course.class));

    }
}
