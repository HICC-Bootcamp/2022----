package com.hicc.tutorking.service;

import com.hicc.tutorking.constant.Role;
import com.hicc.tutorking.entity.Account;
import com.hicc.tutorking.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public Account saveAccount(Account account) {
        validateDuplicateAccount(account);
        return accountRepository.save(account);
    }


    private void validateDuplicateAccount(Account account) {
        Account findAccount = accountRepository.findByEmail(account.getEmail());
        if (findAccount != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public String CheckIdentity(Account account) {
        boolean studentRole = accountRepository.existsByEmailAndRole(account.getEmail(), Role.STUDENT);
        boolean teacherRole = accountRepository.existsByEmailAndRole(account.getEmail(), Role.TEACHER);
        if (studentRole == true) {
            return "student";
        } else if (teacherRole == true) return "teacher";
        else return "not exist";
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(account.getEmail())
                .password(account.getPassword())
                .roles(account.getRole().toString())
                .build();
    }
}

