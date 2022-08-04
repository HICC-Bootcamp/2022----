package com.hicc.tutorking.repository;

import com.hicc.tutorking.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    List<Connection> findByTeacherEmail(String teacherEmail);

    Connection findByStudentEmail(String studentEmail);
}
