package com.hicc.tutorking.service;

import com.hicc.tutorking.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
}
