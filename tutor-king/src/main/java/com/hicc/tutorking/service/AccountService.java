package com.hicc.tutorking.service;

import com.hicc.tutorking.entity.Account;
import com.hicc.tutorking.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
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
}
