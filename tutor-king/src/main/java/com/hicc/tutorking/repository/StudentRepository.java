package com.hicc.tutorking.repository;

import com.hicc.tutorking.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT st FROM Student st LEFT JOIN st.account acc WHERE acc.email = :email")
    Student findByEmail(String email);
}
