package com.david.sample.controller;

import com.david.sample.Service.UserService;
import com.david.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegistrationController {
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
    public String ProcessRegistration(@Valid @ModelAttribute("user")User user,
                                      Model model,
                                      BindingResult bindingResult){
        String theUserName = user.getUsername();
        if (bindingResult.hasErrors()){
            return "register";
        }

        Optional<User> theUser = Optional.ofNullable(userService.findUserByName(theUserName));

        if (theUser.isPresent()){
            model.addAttribute("user",new User());
            model.addAttribute("error","username already exista");
        }

        userService.save(user);

        return "registrationSuccessful";

    }

}
