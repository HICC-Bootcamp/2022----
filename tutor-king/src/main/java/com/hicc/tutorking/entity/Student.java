package com.hicc.tutorking.entity;

import com.hicc.tutorking.dto.StudentInfoDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Getter
@Setter
@ToString
public class Student {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    private String area; //사는 곳
    private String admission; //입학일
    private String subject;
    private String teacherStyle; // 원하는 선생님의 성향
    private int money; //지불하기를 원하는 금액

    public static Student createStudent(StudentInfoDto studentInfoDto) {
        Student student = new Student();
        student.setArea(studentInfoDto.getArea());

        student.setAdmission(studentInfoDto.getAdmission());
        student.setSubject(studentInfoDto.getSubject());
        student.setTeacherStyle(studentInfoDto.getTeacherStyle());
        student.setMoney(studentInfoDto.getMoney());

        return student;

    }
}