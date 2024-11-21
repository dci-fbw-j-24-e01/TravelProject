package dci.j24e01.TravelBlog.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class AppController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
