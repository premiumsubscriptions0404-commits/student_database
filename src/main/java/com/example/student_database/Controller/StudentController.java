package com.example.student_database.Controller;

import java.util.ArrayList;
import java.util.List;

import com.example.student_database.Entity.Course;
import com.example.student_database.Entity.Student;
import com.example.student_database.Service.CourseService;
import com.example.student_database.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/students")
public class StudentController
{
    private StudentService studentService;
    private CourseService courseService;
    public StudentController(StudentService studentService, CourseService courseService)
    {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    // @GetMapping("/")
    // public String findAll(Model model)
    // {
    //     List<Student> students = studentService.findAll();
    //     model.addAttribute("students", students);
    //     return "student-list";

    // }

    @GetMapping("/")
    public ResponseEntity<List<Student>> findAll1()
    {
        List<Student> students = studentService.findAll();
        return ResponseEntity.ok(students); 
    }

    @GetMapping("/add")
    public String add(Model model)
    {
        Student theStudent = new Student();
        model.addAttribute("theStudent", theStudent);
        return "student-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("theStudent") Student theStudent)
    {
        studentService.save(theStudent);
        return "redirect:/students";
    }

    @GetMapping("/{id}/courses")
    public String viewCourses(@PathVariable("id") int id, Model model)
    {
        Student student = studentService.findById(id);
        List<Course> courses = student.getCourses();
        if(courses.isEmpty()) {
            return "redirect:/students/" + id + "/addCourses";
        }
        model.addAttribute("remove_id", id);
        model.addAttribute("courses", courses);
        return "course-list";
    }

    @GetMapping("/{id}/addCourses")
    public String addCourses(@PathVariable("id") int id, Model model)
    {
        List<Course> studentCourses = studentService.findById(id).getCourses();
        List<Course> courses = courseService.findAll();
        List<Course> remainingCourses = new ArrayList<Course>();
        for(Course c: courses)
        {
            if(!studentCourses.contains(c)) {
                remainingCourses.add(c);
            }
        }
        model.addAttribute("courses", remainingCourses);
        model.addAttribute("add_id", id);
        return "course-list";
    }

    @GetMapping("/{sid}/addCourse")
    public String addCourse(@PathVariable("sid") int sid, @RequestParam("cid") int cid)
    {
        Student student = studentService.findById(sid);
        Course course = courseService.findById(cid);
        student.addCourse(course);
        studentService.save(student);
        course.addStudent(student);
        courseService.save(course);
        return "redirect:/students/"+sid+"/courses";
    }

    @GetMapping("/{sid}/removeCourse")
    public String removeCourse(@PathVariable("sid") int sid, @RequestParam("cid") int cid)
    {
        Student student = studentService.findById(sid);
        Course course = courseService.findById(cid);
        student.removeCourse(course);
        studentService.save(student);
        course.removeStudent(student);
        courseService.save(course);
        return "redirect:/students/"+sid+"/courses";
    }
}
