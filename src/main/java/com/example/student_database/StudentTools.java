package com.example.student_database;

import com.example.student_database.Entity.Course;
import com.example.student_database.Repository.StudentRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class StudentTools {

    private final StudentRepository studentRepository;

    public StudentTools(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @McpTool(
            name = "get_student",
            description = "Get student details by student id"
    )
    public Object getStudent(
            @McpToolParam(description = "Student id", required = true) int id
    ) {
        return studentRepository.findById(id)
                .<Object>map(student -> Map.of(
                        "found", true,
                        "student", toResponse(student)
                ))
                .orElseGet(() -> Map.of(
                        "found", false,
                        "message", "No student found with id " + id
                ));
    }

    @McpTool(
            name = "list_students",
            description = "List all available students"
    )
    public List<StudentResponse> listStudents() {
        return studentRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @McpTool(
            name = "get_student_courses",
            description = "List all courses connected to a student by student id"
    )
    public Object getStudentCourses(
            @McpToolParam(description = "Student id", required = true) int id
    ) {
        return studentRepository.findById(id)
                .<Object>map(student -> Map.of(
                        "found", true,
                        "studentId", student.getId(),
                        "studentName", student.getName(),
                        "courses", student.getCourses().stream()
                                .map(this::toResponse)
                                .toList()
                ))
                .orElseGet(() -> Map.of(
                        "found", false,
                        "message", "No student found with id " + id
                ));
    }

    @McpTool(
            name = "calculate_grade",
            description = "Convert marks into grade"
    )
    public String calculateGrade(
            @McpToolParam(description = "Marks from 0 to 100", required = true) int marks
    ) {
        if (marks < 0 || marks > 100) {
            return "Invalid marks. Marks should be between 0 and 100.";
        }

        if (marks >= 90) {
            return "A Grade";
        }
        if (marks >= 80) {
            return "B Grade";
        }
        if (marks >= 70) {
            return "C Grade";
        }
        if (marks >= 60) {
            return "D Grade";
        }

        return "Fail";
    }

    private StudentResponse toResponse(com.example.student_database.Entity.Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getCourses().stream().map(this::toResponse).toList()
        );
    }

    private CourseResponse toResponse(Course course) {
        return new CourseResponse(course.getId(), course.getName());
    }

    public record StudentResponse(
            int id,
            String name,
            List<CourseResponse> courses
    ) {
    }

    public record CourseResponse(
            int id,
            String name
    ) {
    }
}
