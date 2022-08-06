package com.hicc.tutorking.controller;

import com.hicc.tutorking.dto.ConnectionDto;
import com.hicc.tutorking.dto.StudentInfoDto;
import com.hicc.tutorking.entity.Connection;
import com.hicc.tutorking.entity.Student;
import com.hicc.tutorking.entity.Teacher;
import com.hicc.tutorking.repository.TeacherRepository;
import com.hicc.tutorking.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequestMapping("/students")
@Controller
@RequiredArgsConstructor
public class StudentController {


    private final StudentService studentService;
    private final TeacherRepository teacherRepository;

    @GetMapping(value = "/info")
    public String studentForm(Model model) {
        model.addAttribute("studentInfoDto", new StudentInfoDto());
        return "student/student_info";
    }

    @PostMapping(value = "/info")
    public String newStudentInfo(@Valid StudentInfoDto studentInfoDto, BindingResult bindingResult,
                                 Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "student/student_info";
        }

        try {
            String studentEmail = principal.getName();
            Student student = Student.createStudent(studentInfoDto, studentEmail);
            studentService.saveStudent(student);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "student/student_info";
        }


        return "redirect:/students/connections";
    }

    @GetMapping(value = "/connections")
    // 들어갈 건지는 프론트엔드에서 구현함(프론트)
    public String studentMain() {
        studentService.resetHashtag();
        return "student/student_main";
    }

    @GetMapping(value = {"/connections/matching"}) // 학생의 매칭 부분으로 들어감
    public String studentMatch(Principal principal, Model model) {
        String studentEmail = principal.getName();
        studentService.suggest(studentEmail);

        List<Teacher> teachers = studentService.getTeacherList();
        model.addAttribute("teachers", teachers);

        model.addAttribute("connectionDto", new ConnectionDto());
        return "student/student_matching";
    }

    @PostMapping(value = "/connections/matching")
    public String studentConnection(Principal principal, ConnectionDto connectionDto,
                                    BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "student/student_matching";
        }

        String studentEmail = principal.getName();

        try {
            Connection connection = Connection.createConnection(studentEmail, connectionDto);
            studentService.saveConnection(connection);
            studentService.setPhoneNumbers(studentEmail, connectionDto);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "student/student_matching";
        }

        return "redirect:/students/connections";
    }

    @GetMapping(value = "/connections/checks")
    public String studentConnectionCheck(Principal principal, Model model) {
        String studentEmail = principal.getName();

        try {
            studentService.notAsk(studentEmail);
            Connection connection = studentService.checkConnection(studentEmail);
            Student studentInfo = studentService.getStudentInfo(studentEmail);
            Teacher teacherInfo = studentService.getTeacherInfo(studentEmail);
            model.addAttribute("connection", connection);
            model.addAttribute("studentInfo", studentInfo);
            model.addAttribute("teacherInfo", teacherInfo);
        } catch (TemplateInputException | NullPointerException e) {

            return "/student/student_notchoice";
        }

        return "/student/student_waiting";
    }

    @GetMapping(value = "/connections/success")
    public String studentMatchingSuccess(Principal principal, Model model) {
        String studentEmail = principal.getName();
        Teacher teacherInfo = studentService.getTeacherInfo(studentEmail);
        Student studentInfo = studentService.getStudentInfo(studentEmail);
        model.addAttribute("teacherInfo", teacherInfo);
        model.addAttribute("studentInfo", studentInfo);
        return "connect/connect_success";
    }

    @GetMapping(value = "/connections/fail")
    public String studentMatchingFail(Principal principal, Model model) {
        String studentEmail = principal.getName();
        Teacher teacherInfo = studentService.getTeacherInfo(studentEmail);
        Student studentInfo = studentService.getStudentInfo(studentEmail);
        model.addAttribute("teacherInfo", teacherInfo);
        model.addAttribute("studentInfo", studentInfo);
        return "student/student_failed";
    }


}



