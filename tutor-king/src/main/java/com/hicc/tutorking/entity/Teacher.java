package com.hicc.tutorking.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="teacher")
@Getter
@Setter
@ToString
public class Teacher {

    @Id
    @Column(name="teacher_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    private String area;
    private int age;
    private String style;
    private String subject;
    private String target;
    private String university;
    private String major;
    private int wage;
    private String experience;




}