package dci.j24e01.TravelBlog.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        boolean isLoggedIn = authentication != null && authentication.isAuthenticated();
        model.addAttribute("loggedIn", isLoggedIn);
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "login";
    }

    @GetMapping("/admin_panel")
    public String adminPanel(Model model, Authentication authentication) {
        boolean isLoggedIn = authentication != null && authentication.isAuthenticated();
        model.addAttribute("loggedIn", isLoggedIn);
        return "admin_panel";
    }
}
