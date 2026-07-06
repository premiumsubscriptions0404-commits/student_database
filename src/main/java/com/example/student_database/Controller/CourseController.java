package com.example.student_database.Controller;

import java.util.List;

import com.example.student_database.Entity.Course;
import com.example.student_database.Entity.Review;
import com.example.student_database.Service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/courses")
public class CourseController
{
    
    private CourseService courseService;

    public CourseController(CourseService courseService)
    {
        this.courseService = courseService;
    }

    @GetMapping("")
    public String findAll(Model model)
    {
        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "course-list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model)
    {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);
        List<Review> reviews = course.getReviews();
        model.addAttribute("reviews", reviews);
        return "course-detail";
    }

    @GetMapping("/add")
    public String add(Model model)
    {
        Course theCourse = new Course();
        model.addAttribute("theCourse", theCourse);
        return "course-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("theCourse") Course theCourse)
    {
        courseService.save(theCourse);
        return "redirect:/courses";
    }

}