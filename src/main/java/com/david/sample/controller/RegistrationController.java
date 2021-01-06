package com.david.sample.controller;

import com.david.sample.Service.UserService;
import com.david.sample.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class RegistrationController {
    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/register")
    public String login(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/processRegistration")
    public String ProcessRegistration(@Valid@ModelAttribute("user") User user,
                                      BindingResult bindingResult,Model model){
        String theUserName = user.getUserName();
        if (bindingResult.hasErrors()){
            return "redirect:/register";
        }

        User theUserExists =userService.findUserByName(theUserName);
        logger.info("The return value is "+theUserExists.toString());

        if (theUserExists != null){
            model.addAttribute("user",new User());
            model.addAttribute("error","username already exists");
            return "redirect:/register";
        }else {
            userService.save(user);
        }

        return "registrationSuccessful";

    }

}
