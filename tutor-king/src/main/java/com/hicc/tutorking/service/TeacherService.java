package com.hicc.tutorking.service;


import com.hicc.tutorking.dto.TeacherReplyDto;
import com.hicc.tutorking.entity.Account;
import com.hicc.tutorking.entity.Connection;
import com.hicc.tutorking.entity.Student;
import com.hicc.tutorking.entity.Teacher;
import com.hicc.tutorking.repository.AccountRepository;
import com.hicc.tutorking.repository.ConnectionRepository;
import com.hicc.tutorking.repository.StudentRepository;
import com.hicc.tutorking.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherService {
    private final StudentRepository studentRepository;
    private final AccountRepository accountRepository;
    private final TeacherRepository teacherRepository;
    public String currentStudentEmail;
    private final ConnectionRepository connectionRepository;

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Student> InfoStudentWhoAsked(String teacherEmail) {

        List<Connection> connections = connectionRepository.findByTeacherEmail(teacherEmail);
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < connections.size(); i++) {
            Connection temp = connections.get(i);
            String studentEmail = temp.getStudentEmail();

            Student studentWhoAsked = studentRepository.findByStudentEmail(studentEmail);
            studentList.add(studentWhoAsked);
        }

        return studentList;

    }

    public void AddTeacherReply(String teacherEmail, TeacherReplyDto teacherReplyDto) {

        Connection connection = connectionRepository.findByStudentEmail(teacherReplyDto.getStudentEmail());
        connection.setTeacherReply(teacherReplyDto.getTeacherReply());
        currentStudentEmail = teacherReplyDto.getStudentEmail();
    }

    public Teacher getTeacherInfo(String teacherEmail) {
        Teacher teacher = teacherRepository.findByTeacherEmail(teacherEmail);
        return teacher;
    }

    public Student getStudentInfo(String teacherEmail) {

        Student student = studentRepository.findByStudentEmail(currentStudentEmail);

        return student;
    }

    public Account getAccount(String teacherEmail) {
        Account account = accountRepository.findByEmail(teacherEmail);
        return account;
    }


}
