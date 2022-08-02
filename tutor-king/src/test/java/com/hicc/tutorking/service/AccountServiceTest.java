/*package com.hicc.tutorking.service;

import com.hicc.tutorking.dto.AccountFormDto;
import com.hicc.tutorking.entity.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Account createAccount() {
        AccountFormDto accountFormDto = new AccountFormDto();
        accountFormDto.setEmail("test1@email.com");
        accountFormDto.setPassword("1234");
        accountFormDto.setName("홍길동");
        accountFormDto.setPhone_number("01033074335");
        accountFormDto.setType("student");
        return Account.createAccount(accountFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveAccountTest() {
        Account account = createAccount();
        Account savedAccount= accountService.saveAccount(account);

        assertEquals(account.getEmail(), savedAccount.getEmail());
        assertEquals(account.getPassword(), savedAccount.getPassword());
        assertEquals(account.getName(), savedAccount.getName());
        assertEquals(account.getPhone_number(), savedAccount.getPhone_number());
        assertEquals(account.getType(), savedAccount.getType());
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateAccountTest() {
        Account account1 = createAccount();
        Account account2 = createAccount();
        accountService.saveAccount(account1);
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            accountService.saveAccount(account2);
        });
        assertEquals("이미 가입된 회원입니다.", e.getMessage());
    }
}*/