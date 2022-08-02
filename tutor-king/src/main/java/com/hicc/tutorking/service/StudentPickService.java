package com.hicc.tutorking.service;

import com.hicc.tutorking.entity.StudentPick;
import com.hicc.tutorking.repository.StudentPickRepository;
import com.hicc.tutorking.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentPickService {

    private final StudentPickRepository studentpickRepository;




}
