package dci.j24e01.TravelBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String login() {
        return "login";
    }

    @PostMapping("/admin")
    public String admin() {
        System.out.println("tryed to login");
        return "redirect:/";
    }
}
