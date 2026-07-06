package com.example.student_database.Controller;

import com.example.student_database.Entity.Passport;
import com.example.student_database.Entity.Student;
import com.example.student_database.Service.PassportService;
import com.example.student_database.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/passports")
public class PassportController
{
    
    private StudentService studentService;
    private PassportService passportService;

    public PassportController(StudentService studentService, PassportService passportService)
    {
        this.studentService = studentService;
        this.passportService = passportService;
    }

    @GetMapping("/add")
    public String add(Model model, @RequestParam("id") int id)
    {
        Passport thePassport = new Passport();
        model.addAttribute("thePassport", thePassport);
        model.addAttribute("id", id);
        return "passport-form";
    }

    @PostMapping("/save")
    public String save(@RequestParam("id") int id, @ModelAttribute("thePassport") Passport thePassport)
    {
        Student student = studentService.findById(id);
        thePassport.setStudent(student);
        passportService.save(thePassport);
        student.setPassport(thePassport);
        studentService.save(student);
        return "redirect:/students";
    }

}
