package com.hicc.tutorking.controller;


import com.hicc.tutorking.dto.AccountFormDto;
import com.hicc.tutorking.entity.Account;
import com.hicc.tutorking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/auth")
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value="/signup")
    public String accountForm(Model model){
        model.addAttribute("accountFormDto",new AccountFormDto());
        return "signup/signup";
    }


    @PostMapping(value="/signup")
    public String getCreateNewAccountView(@Valid AccountFormDto accountFormDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "signup/signup";
        }

        try {
            Account account = Account.createAccount(accountFormDto, passwordEncoder);
            accountService.saveAccount(account);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup/signup";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginAccount(){
        return "/login/login";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/login/login";
    }



}
