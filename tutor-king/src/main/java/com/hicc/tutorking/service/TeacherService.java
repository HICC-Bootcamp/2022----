package com.hicc.tutorking.service;

import com.hicc.tutorking.dto.TeacherInfoDto;
import com.hicc.tutorking.entity.Teacher;
import com.hicc.tutorking.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    public Long saveTeacher(TeacherInfoDto teacherInfoDto) throws Exception {
        Teacher teacher = teacherInfoDto.createTeacher();
        teacherRepository.save(teacher);


        return teacher.getId();
    }
}
