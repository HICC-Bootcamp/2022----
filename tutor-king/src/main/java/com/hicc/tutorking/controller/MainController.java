package com.hicc.tutorking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @RequestMapping("/main")
    public String mainAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("STUDENT")) {
            return "redirect:/students/connections";
        }
        return "redirect:/teachers/connections";
    }
}
