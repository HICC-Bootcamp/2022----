package com.hicc.tutorking.controller;

import com.hicc.tutorking.dto.TeacherInfoDto;
import com.hicc.tutorking.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/teachers")
@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping(value="/info")
    public String studentForm(Model model){
        model.addAttribute("teacherInfoDto",new TeacherInfoDto());
        return "teacher/teacher_info";
    }

    @PostMapping(value="/info")
    public String newTeacherInfo(@Valid TeacherInfoDto teacherInfoDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return  "teacher/teacher_info";
        }

        return "redirect:/";
    }

    // @GetMapping(value="/connections")

}

