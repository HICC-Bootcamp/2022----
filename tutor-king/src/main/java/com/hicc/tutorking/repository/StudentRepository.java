package com.hicc.tutorking.repository;

import com.hicc.tutorking.entity.Account;
import com.hicc.tutorking.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByAccount(Account temp);

}
