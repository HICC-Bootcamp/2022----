package com.hicc.tutorking.service;

import com.hicc.tutorking.repository.AccountRepository;
import com.hicc.tutorking.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
}
