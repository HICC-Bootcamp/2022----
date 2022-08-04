package com.hicc.tutorking.repository;

import com.hicc.tutorking.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findOrderByHashtagDesc();

    @Query("SELECT te FROM Teacher te LEFT JOIN te.account acc WHERE acc.email = :email")
    Teacher findByEmail(String email);

}
