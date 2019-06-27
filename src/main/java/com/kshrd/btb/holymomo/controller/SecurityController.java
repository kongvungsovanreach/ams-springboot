package com.kshrd.btb.holymomo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {
    @GetMapping("access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }

    @GetMapping("/custom-login")
    public String customLogin() {
        return "custom-login";
    }

    @GetMapping("/profile/user")
    @ResponseBody
    public String userProfile() {

        return "USER PROFILE SECURITY TESTING";
    }

    @GetMapping("/profile/admin")
    @ResponseBody
    public String adminProfile() {

        return "ADMIN PROFILE SECURITY TESTING";
    }
}