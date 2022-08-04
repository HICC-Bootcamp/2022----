package com.hicc.tutorking.controller;

import com.hicc.tutorking.dto.ConnectionDto;
import com.hicc.tutorking.dto.TeacherInfoDto;
import com.hicc.tutorking.dto.TeacherReplyDto;
import com.hicc.tutorking.entity.Connection;
import com.hicc.tutorking.entity.Student;
import com.hicc.tutorking.entity.Teacher;
import com.hicc.tutorking.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public String newTeacherInfo(@Valid TeacherInfoDto teacherInfoDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "teacher/teacher_info";
        }

        try {
            Teacher teacher = Teacher.createTeacher(teacherInfoDto);
            teacherService.saveTeacher(teacher);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "teacher/teacher_info";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/connections")
    public String teacherMain() {  //TODO:teachers/connections/로 가는 건 프론트엔드에서 구현
        return "teacher/teacher_main";
    }

    @GetMapping(value = "/connections/requests") // 여기로 가게 해주세용
    public String teacherConnection(Principal principal, Model model) {
        String teacherEmail = principal.getName();

        List<Student> InfoStudentWhoAsked = teacherService.InfoStudentWhoAsked(teacherEmail);
        List<Connection> EmailStudentWhoAskedEmail = teacherService.EmailStudentWhoAsked(teacherEmail);

        model.addAttribute("StudentWhoAskedInfo", InfoStudentWhoAsked); //우리가 넘겨주는 거 선생님한테 매칭을 요청한 학생들의 정보들
        model.addAttribute("connections", EmailStudentWhoAskedEmail); //우리가 넘겨주는 거 선생님한테 매칭을 요청한 학생들의 이메일주소
        model.addAttribute("teacherReplyDto", new TeacherReplyDto());
        // TODO: 선생님한테 매칭을 요청한 학생들의 리스트를 프론트에게 준다. 프론트엔드에서 teacherReplyDto에 수락버튼을 눌렀을때
        // 그 수락한 학생의 이메일을 보내줘야 함
        // 리스트에서 각각 학생들 정보를 꺼내서 프론트에 보이게 하는 것
        // * List로 학생의 info 에 맞는 선생님들의 리스트를 보내줌
        // 프론트엔드에서 사용자가 선생님을 선택한다. 그 선생님의 이메일을 백엔드에게 전달해줘야함 *
        return "teacher/teacher_matching";

    }

    @PostMapping(value = "/connections/requests")
    public String teacherConnection(Principal principal, ConnectionDto connectionDto,
                                    BindingResult bindingResult, Model model) {

    }
}