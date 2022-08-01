package com.hicc.tutorking.service;

import com.hicc.tutorking.dto.StudentInfoDto;
import com.hicc.tutorking.entity.Student;
import com.hicc.tutorking.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Long saveStudent(StudentInfoDto studentInfoDto) throws Exception {
        Student student = studentInfoDto.createStudent();
        studentRepository.save(student);


        return student.getId();
    }
}
