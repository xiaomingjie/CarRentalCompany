package com.carrentalcompany.controller;

import com.carrentalcompany.model.User;
import com.carrentalcompany.response.Result;
import com.carrentalcompany.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/register")
    public String register(User user, String password2, Model model, RedirectAttributes redirectAttributes) {
        Result result = userService.register(user, password2);
        redirectAttributes.addFlashAttribute("msg", result.getMsg());
        if(result.isSuccess()){
            return "redirect:/CarRentalCompany/login";
        }
        else{
            return "redirect:/CarRentalCompany/register";
        }
    }

    @PostMapping("/logout")
    public String logout(){
        return "main";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
