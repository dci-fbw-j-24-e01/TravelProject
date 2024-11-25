package dci.j24e01.TravelBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/admin_panel")
    public String adminPanel() {
        System.out.println("test");
        return "admin_panel";
    }
}
