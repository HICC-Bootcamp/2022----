package com.hicc.tutorking.controller;

import com.hicc.tutorking.dto.StudentInfoDto;
import com.hicc.tutorking.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/students")
@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping(value="/info")
    public String studentForm(Model model){
        model.addAttribute("studentInfoDto",new StudentInfoDto());
        return "student/student_info";
    }

    @PostMapping(value="/info")
    public String newStudentInfo(@Valid StudentInfoDto studentInfoDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return  "student/student_info";
        }

        try {
            studentService.saveStudent(studentInfoDto);
        } catch (Exception e){
            model.addAttribute("errorMessage","에러 발생했습니다.");
            return "student/student_info";
        }

        return "redirect:/";
    }

    //@GetMapping(value="/connections")

}
