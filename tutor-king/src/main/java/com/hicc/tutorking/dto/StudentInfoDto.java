package com.hicc.tutorking.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class StudentInfoDto {

    private Long id;

    @NotBlank(message="거주 지역은 필수 입력 값입니다.")
    private String area;

    @NotBlank(message = "입학일은 필수 입력 값입니다.")
    private String admission; //입학일

    @NotBlank(message = "관심 과목은 필수 입력 값입니다.")
    private String subject;

    @NotBlank(message = "원하는 선생님의 성향은 필수 입력 값입니다.")
    private String teacherStyle; // 원하는 선생님의 성향

    @NotBlank(message = "희망 과외비는 필수 입력 값입니다.")
    private int money;

/*
    private static ModelMapper modelMapper = new ModelMapper();

    public Student createStudent(){
        return modelMapper.map(this,Student.class);
    }

    public static StudentInfoDto of (Student student){
        return modelMapper.map(student,StudentInfoDto.class);
    }

*/


}
