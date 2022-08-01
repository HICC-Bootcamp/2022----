package com.hicc.tutorking.repository;

import com.hicc.tutorking.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
