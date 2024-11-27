package dci.j24e01.TravelBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DetailController {
    @GetMapping("/detail")
    public String getDetail(Model model) {
        return "detail";
    }
}
