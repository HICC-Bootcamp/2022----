package com.hicc.tutorking.controller;

import com.hicc.tutorking.dto.TeacherInfoDto;
import com.hicc.tutorking.dto.TeacherReplyDto;
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
                                 Model model,Principal principal) {
        if (bindingResult.hasErrors()) {
            return "teacher/teacher_info";
        }

        try {
            String teacherEmail=principal.getName();
            Teacher teacher = Teacher.createTeacher(teacherInfoDto,teacherEmail);
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

        return "redirect:/teachers/connections";

    }

    @GetMapping(value="/connections/success")
    public String teacherMatchingSuccess(Principal principal, Model model){
        String teacherEmail= principal.getName();
        teacherService.getTeacherInfo(teacherEmail);
        teacherService.getStudentInfo(teacherEmail);
        return "connect/connect_success";
    }

    //String ShowStudentNumber(teacherReplyDto)

    //TODO: !!!프론트엔드!!!
    // 선생님 버전; 선생님한테 매칭을 요청한 학생들의 정보와 이메일을 백엔드가 프론트에게 줌.("StudentWhoAskedInfo","EmailStudentWhoAsked")
    // 프론트엔드에서 그 리스트에서 각 학생들 정보 꺼내서 프론트엔드에 보이게 하는 것,
    // 선생님이 수락/거절한 학생의 이메일과 수락했는지 거절했는지의 여부를
    // teacherReplyDto를 통해 백엔드에 보내줘야 함

    //TODO: !!!프론트엔드!!!
    // 학생 버전;  학생의 info 에 맞는 선생님들의 리스트를 백엔드가 보내줌("teachers"로 보내줌)
    // 리스트에서 각각 선생님들 정보를 꺼내서 프론트에 보이게 하는 것
    //  프론트엔드에서 사용자가 선생님을 선택한다. 그 선택한 선생님의 이메일을 ConnectionDto를 통해서 백엔드에게 전달해줘야함 *



    //TODO:프론트엔드에서 선생님은 학생 수락이나 거절이나 버튼 누르면 그 버튼들을 다신 못 누르게 했으면 좋겠습니당



}