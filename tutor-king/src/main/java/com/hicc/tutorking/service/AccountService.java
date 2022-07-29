package com.hicc.tutorking.service;

import com.hicc.tutorking.entity.Account;
import com.hicc.tutorking.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountService
        //implements  UserDetailsService
{
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

    /*
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Account account = accountRepository.findByEmail(email);

        if(account == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()//User객체를 반환할 때, 이메일 비밀번호 타입을 파라미터로 넘겨줌
                .username(account.getEmail())
                .password(account.getPassword())
                .roles(account.getRole().toString())
                .build();
    }
    */



}
