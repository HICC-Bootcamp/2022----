package com.hicc.tutorking.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TeacherReplyDto {

    private Long id;

    @NotBlank(message="수락/거절을 선택해주세요.")
    private Boolean teacherReply;

    private String studentEmail; //학생 메일
}
