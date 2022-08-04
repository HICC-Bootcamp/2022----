package com.hicc.tutorking.entity;

import com.hicc.tutorking.dto.ConnectionDto;
import com.hicc.tutorking.dto.TeacherInfoDto;
import com.hicc.tutorking.dto.TeacherReplyDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="connection")
@Getter
@Setter
@ToString
public class Connection {
    @Id
    @Column(name="connection_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String studentEmail;
    private String teacherEmail;
    private Boolean teacherReply;

    // TODO: 자신의 이메일 주소(학생)를 principal로 받아와서, 이걸 connection repository 에서 찾는다
    // TODO: 그래서 그 findByEmail 로 찾은 connection repository 에서 teacherEmail을 찾아서 그걸로
    //  teacher repository를 찾아서 그 레포안에 있는 선생님의 정보를 프론트엔드에 전달해준다
    // TODO: 그래서 그 findByEmail 로 찾은 connection repository 에서 teacherReply에 뭐가 담겨있는지를 프론트엔드에 전달해준다

    public static Connection createConnection(String studentEmail, ConnectionDto connectionDto) {
        Connection connection = new Connection();
        connection.setStudentEmail(studentEmail);
        connection.setTeacherEmail(connectionDto.getTeacherEmail());
        connection.setTeacherReply(null);

        return connection;
    }


}
