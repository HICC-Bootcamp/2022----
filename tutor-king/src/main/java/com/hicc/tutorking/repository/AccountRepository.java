package com.hicc.tutorking.repository;

import com.hicc.tutorking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByEmail(String email);
}
