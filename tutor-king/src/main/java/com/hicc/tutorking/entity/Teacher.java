package com.hicc.tutorking.entity;


import com.hicc.tutorking.dto.StudentInfoDto;
import com.hicc.tutorking.dto.TeacherInfoDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@ToString
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    private String area;
    private String age;
    private String style;
    private String subject;
    private String target;
    private String university;
    private String major;
    private String wage;
    private String experience;

    public static Teacher createTeacher(TeacherInfoDto teacherInfoDto) {
        Teacher teacher = new Teacher();
        teacher.setArea(teacherInfoDto.getArea());
        teacher.setAge(teacherInfoDto.getAge());
        teacher.setStyle(teacherInfoDto.getStyle());
        teacher.setSubject(teacherInfoDto.getSubject());
        teacher.setTarget(teacherInfoDto.getTarget());
        teacher.setUniversity(teacherInfoDto.getUniversity());
        teacher.setMajor(teacherInfoDto.getMajor());
        teacher.setWage(teacherInfoDto.getWage());
        teacher.setExperience(teacherInfoDto.getExperience());
        return teacher;

    }


}