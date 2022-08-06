package com.hicc.tutorking.controller;

import com.hicc.tutorking.dto.TeacherInfoDto;
import com.hicc.tutorking.dto.TeacherReplyDto;
import com.hicc.tutorking.entity.Account;
import com.hicc.tutorking.entity.Connection;
import com.hicc.tutorking.entity.Student;
import com.hicc.tutorking.entity.Teacher;
import com.hicc.tutorking.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequestMapping("/teachers")
@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping(value = "/info")
    public String studentForm(Model model) {
        model.addAttribute("teacherInfoDto", new TeacherInfoDto());
        return "teacher/teacher_info";
    }

    @PostMapping(value = "/info")
    public String newTeacherInfo(@Valid TeacherInfoDto teacherInfoDto, BindingResult bindingResult,
                                 Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "teacher/teacher_info";
        }

        try {
            String teacherEmail = principal.getName();
            Account account = teacherService.getAccount(teacherEmail);
            Teacher teacher = Teacher.createTeacher(teacherInfoDto, teacherEmail, account);
            teacherService.saveTeacher(teacher);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "teacher/teacher_info";
        }

        return "redirect:/teachers/connections";
    }

    @GetMapping(value = "/connections")
    public String teacherMain() {  //TODO:버튼 누르면 teachers/connections/requests로 가는 건 프론트엔드에서 구현
        return "teacher/teacher_main";
    }

    @GetMapping(value = "/connections/checks")
    public String teacherConnection(Principal principal, Model model) {
        String teacherEmail = principal.getName();

        List<Student> InfoStudentWhoAsked = teacherService.InfoStudentWhoAsked(teacherEmail);

        model.addAttribute("studentInfo", InfoStudentWhoAsked); //선생님한테 매칭을 요청한 학생들의 정보들
        model.addAttribute("teacherReplyDto", new TeacherReplyDto());//우리가 프론트엔드에 학생이메일이랑 수락 정보를 받음

        return "teacher/teacher_matching";

    }

    @PostMapping(value = "/connections/checks")
    public String teacherConnection(Principal principal, TeacherReplyDto teacherReplyDto,
                                    BindingResult bindingResult, Model model) {

        String teacherEmail = principal.getName();
        teacherService.AddTeacherReply(teacherEmail, teacherReplyDto);

        if (bindingResult.hasErrors()) {
            return "teacher/teacher_matching";
        }

        if (teacherReplyDto.getTeacherReply().equals("수락")) {
            return "redirect:/teachers/connections/success";
        } else if (teacherReplyDto.getTeacherReply().equals("거절")) {
            model.addAttribute("errorMessage", "거절했습니다.");
            return "teacher/teacher_matching";
        }
        return "teacher/teacher_matching";

    }

    @GetMapping(value = "/connections/success")
    public String teacherMatchingSuccess(Principal principal, Model model) {
        String teacherEmail = principal.getName();
        Teacher teacherInfo = teacherService.getTeacherInfo(teacherEmail);
        Student studentInfo = teacherService.getStudentInfo(teacherEmail);
        model.addAttribute("teacherInfo", teacherInfo);
        model.addAttribute("studentInfo", studentInfo);
        return "connect/connect_success";
    }
}