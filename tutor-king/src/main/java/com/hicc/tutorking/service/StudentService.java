package com.hicc.tutorking.service;

import com.hicc.tutorking.dto.ConnectionDto;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final AccountRepository accountRepository;

    private final ConnectionRepository connectionRepository;
    private final TeacherRepository teacherRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Connection saveConnection(Connection connection) {
        return connectionRepository.save(connection);
    }

    public void resetHashtag() {

        List<Teacher> teachers = teacherRepository.findAll();

        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);
            teacher.setHashtag(0);
        }
    }

    public void suggest(String email) {

        Student student = studentRepository.findByEmail(email);
        List<Teacher> teachers = teacherRepository.findAll();

        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);
            teacher.setHashtag(0);
        }

        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);
            int same = 0; //몇 개가 맞는지

            if (teacher.getArea().equals(student.getArea())) {
                same++;
            }
            if (teacher.getStyle().equals(student.getTeacherStyle())) {
                same++;
            }
            if ((teacher.getSubject().equals(student.getTeacherStyle()))) {
                same++;
            }
            if (teacher.getTarget().equals(student.getAdmission())) {
                same++;
            }

            if (teacher.getWage() <= (student.getMoney()) + 9999) {
                same++;
            }

            teacher.setHashtag(same);
        }

    }

    @Transactional(readOnly = true)
    public List<Teacher> getTeacherList() {
        return teacherRepository.findAll(Sort.by(Sort.Direction.DESC, "hashtag"));
    }

    public Connection checkConnection(String studentEmail) {
        Connection connection = connectionRepository.findByStudentEmail(studentEmail);
        return connection;
    }

    public void setPhoneNumbers(String studentEmail, ConnectionDto connectionDto) {
        Account student = accountRepository.findByEmail(studentEmail);
        Account teacher = accountRepository.findByEmail(connectionDto.getTeacherEmail());
        connectionDto.setStudentPhoneNumber(student.getPhoneNumber());
        connectionDto.setTeacherPhoneNumber(teacher.getPhoneNumber());

    }

    public Student getStudentInfo(String studentEmail){
        Student student=studentRepository.findByEmail(studentEmail);
        return student;
    }

    public Teacher getTeacherInfo(String studentEmail){
        Connection connection=connectionRepository.findByStudentEmail(studentEmail);
        Teacher teacher=teacherRepository.findByEmail(connection.getTeacherEmail());
        return teacher;
    }


}




