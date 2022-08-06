package com.hicc.tutorking.repository;

import com.hicc.tutorking.constant.Role;
import com.hicc.tutorking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);//회원 가입 시 중복 회원이 있는지 검사하기 위해서 이메일로 회원을 검사할 수 있는 코드 작성
    boolean existsByEmailAndRole(String email, Role role);
}
