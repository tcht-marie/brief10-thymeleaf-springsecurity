package simplon.spring.security.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import simplon.spring.security.dto.RegisterDto;
import simplon.spring.security.model.User;
import simplon.spring.security.service.UserService;

import java.util.Optional;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        Optional<User> user = userService.from(authentication);
        /*if (user.isPresent()) {
            model.addAttribute("currentUser", user.get());
        }*/
        user.ifPresent(value -> model.addAttribute("currentUser", value));
        return "forknow-index";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/account")
    public String account(Model model, Authentication authentication) {
        Optional<User> user = userService.from(authentication);
        /*if (user.isPresent()) {
            model.addAttribute("currentUser", user.get());
        }*/
        user.ifPresent(value -> model.addAttribute("currentUser", value));
        return "account";
    }

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto user = new RegisterDto();
        model.addAttribute("userInfo", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registerUser(@Valid @ModelAttribute RegisterDto userMapping) {
        if (!userMapping.getPassword().equals(userMapping.getPasswordConfirm())) {
            return "redirect:/register?error";
        }
        userService.saveUser(userMapping);
        return "redirect:/login?success=userRegistered";
    }

    /*@GetMapping("/admin")
    public String admin() {
        return "admin";
    }*/
}
