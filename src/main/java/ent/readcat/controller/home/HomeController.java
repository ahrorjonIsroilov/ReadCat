package ent.readcat.controller.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome home";
    }

    @GetMapping("/welcome/lock")
    public String protectedWelcome(){
        return "Welcome to protected room 🤣";
    }
}