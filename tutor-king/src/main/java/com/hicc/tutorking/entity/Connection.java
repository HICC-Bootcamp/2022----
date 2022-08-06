package com.hicc.tutorking.entity;

import com.hicc.tutorking.dto.ConnectionDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "connection")
@Getter
@Setter
@ToString
public class Connection {
    @Id
    @Column(name = "connection_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String studentEmail;
    private String teacherEmail;
    private String teacherReply;
    private String studentPhoneNumber;
    private String teacherPhoneNumber;

    public static Connection createConnection(String studentEmail, ConnectionDto connectionDto) {
        Connection connection = new Connection();
        connection.setStudentEmail(studentEmail);
        connection.setTeacherEmail(connectionDto.getTeacherEmail());
        connection.setTeacherReply("미확인");

        return connection;
    }


}
