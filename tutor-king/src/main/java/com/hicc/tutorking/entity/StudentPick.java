package com.hicc.tutorking.entity;

//무슨 dto를 써야할 지 모르겠음....
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "studentpick")
@Getter
@Setter
@ToString
public class StudentPick {
    @Id//primary key 생성
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
