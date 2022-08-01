package com.hicc.tutorking.entity;

import com.hicc.tutorking.constant.Role;
import com.hicc.tutorking.dto.AccountFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="account")
@Getter
@Setter
@ToString
public class Account {
    @Id
    @Column(name="account_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Account createAccount(AccountFormDto accountFormDto, PasswordEncoder passwordEncoder){
        Account account = new Account();
        account.setEmail(accountFormDto.getEmail());
        account.setName(accountFormDto.getName());
        account.setPhoneNumber(accountFormDto.getPhoneNumber());
        String password=passwordEncoder.encode(accountFormDto.getPassword());
        account.setPassword(password);

        if((accountFormDto.getRole()).equals("student")){
            account.setRole(Role.STUDENT);
        }
        else if((accountFormDto.getRole()).equals("teacher")){
            account.setRole(Role.TEACHER);
        }

        return account;

    }

}
