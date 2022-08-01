package com.hicc.tutorking.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

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

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    private String area; //사는 곳
    private int admission; //입학일
    private String subject;
    private String teacher_style; // 원하는 선생님의 성향
    private int money; //지불하기를 원하는 금액





}