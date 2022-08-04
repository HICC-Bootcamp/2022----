package com.hicc.tutorking.service;

import com.hicc.tutorking.dto.TeacherReplyDto;
import com.hicc.tutorking.entity.Connection;
import com.hicc.tutorking.entity.Student;
import com.hicc.tutorking.entity.Teacher;
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

    private final TeacherRepository teacherRepository;

    private final ConnectionRepository connectionRepository;
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Student> InfoStudentWhoAsked(String teacherEmail){
        List<Connection> connections=connectionRepository.findByEmail(teacherEmail);
        List<Student> studentList=new ArrayList<>();
        for(int i=0;i<connections.size();i++){
            Connection temp=connections.get(i);
            String studentEmail= temp.getStudentEmail();

            Student studentWhoAsked= studentRepository.findByEmail(studentEmail);
            studentList.add(studentWhoAsked);
        }

        return studentList;

    }

    public List<Connection> EmailStudentWhoAsked(String teacherEmail){
        List<Connection> connections=connectionRepository.findByEmail(teacherEmail);
        return connections;
    }
    public List<Connection> getTeacherReply(TeacherReplyDto teacherReplyDto){


    }
}
