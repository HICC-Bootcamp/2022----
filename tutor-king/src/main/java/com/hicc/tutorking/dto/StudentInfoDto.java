package com.hicc.tutorking.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class StudentInfoDto {

    private Long id;

    @NotBlank(message = "거주 지역은 필수 입력 값입니다.")
    private String area;

    @NotBlank(message = "입학일은 필수 입력 값입니다.")
    private String admission; //입학일

    @NotBlank(message = "관심 과목은 필수 입력 값입니다.")
    private String subject;

    @NotBlank(message = "원하는 선생님의 성향은 필수 입력 값입니다.")
    private String teacherStyle; // 원하는 선생님의 성향

    @PositiveOrZero(message = "지불을 원하는 시급을 선택해주세요 ")
    private Integer money;

}
