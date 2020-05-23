package com.exam9.forum.controller;

import com.exam9.forum.exception.ResourceNotFoundException;
import com.exam9.forum.model.User;
import com.exam9.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserRepository ur;

    private final PasswordEncoder encoder;

    public UserController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model){
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new User());
        }

        return "registration";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @PostMapping("/registration")
    public String createUser(@Valid User user, BindingResult br, RedirectAttributes ra
    ) {
        ra.addFlashAttribute("form", user);
        if (br.hasFieldErrors()){
            ra.addFlashAttribute("errors",br.getFieldErrors());
            return "redirect:/registration";
        }else {
            System.out.println(user);
            user.setPassword(encoder.encode(user.getPassword()));
            ur.save(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/profile")
    public String pageCustomerProfile(Model model, Principal principal)
    {
        try {
            var user = ur.findUserByMail(principal.getName());
            model.addAttribute("dto", user);
        }
        catch (NullPointerException e){
            model.addAttribute("error", "Пользователь не найден!");
        }
        return "profile";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private String handleRNF(ResourceNotFoundException ex, Model model) {
        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
    }
}
