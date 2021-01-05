package com.david.sample.controller;

import com.david.sample.Service.JokeService;
import com.david.sample.model.Joke;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class HomeController {
    private final Logger logger=LoggerFactory.getLogger(HomeController.class);
    private final JokeService jokeService;

    @Autowired
    public HomeController(JokeService jokeService){
        this.jokeService = jokeService;
    }

    @GetMapping("/")
    public String showHome(Model model){
        model.addAttribute("jokes",jokeService.getAllJokes());
        return "index";
    }

    @GetMapping("/showForm")
    public String showForm(Model model){
        model.addAttribute("joke",new Joke());
        return "savejoke";
    }

    @PostMapping("/savejoke")
    public String saveJoke(@Valid@ModelAttribute("joke")Joke joke, BindingResult result){
        if (result.hasErrors()){
            return "redirect:/";
        }
        logger.info("new joke ==== "+joke.getJoke());
        jokeService.save(joke);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateJoke(@PathVariable Long id, Model model){
        model.addAttribute("joke",jokeService.getJoke(id));
        return "savejoke";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        jokeService.delete(id);
        return "redirect:/";
    }
}
