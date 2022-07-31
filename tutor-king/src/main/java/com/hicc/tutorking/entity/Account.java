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

    private String type; //선생님 학생 type

    private String phone_number;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Account createAccount(AccountFormDto accountFormDto, PasswordEncoder passwordEncoder){
        Account account = new Account();
        account.setEmail(accountFormDto.getEmail());
        account.setName(accountFormDto.getName());
        account.setPhone_number(accountFormDto.getPhone_number());
        account.setType(accountFormDto.getType());
        String password=passwordEncoder.encode(accountFormDto.getPassword());
        account.setPassword(password);

        if((accountFormDto.getType()).equals("student")){
            account.setRole(Role.STUDENT);
        }
        else if((accountFormDto.getType()).equals("teacher")){
            account.setRole(Role.TEACHER);
        }

        return account;

    }

}
