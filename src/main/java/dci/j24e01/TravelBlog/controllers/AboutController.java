package dci.j24e01.TravelBlog.controllers;

import dci.j24e01.TravelBlog.models.Creator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//PROVISIONAL

@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(Model model) {
        List<Creator> creators = getCreatorsFromCMS();
        model.addAttribute("creators", creators);
        return "about";
    }

    private List<Creator> getCreatorsFromCMS() {
        // PROVISIONAL MOCKUP
        return List.of(
                new Creator("Alice Johnson", "Content Writer", "Passionate about travel and storytelling. ", "/images/alice.jpg"),
                new Creator("Bob Smith", "Photographer", "Capturing the world's beauty one shot at a time.", "/images/bob.jpg"),
                new Creator("Claire Brown", "Editor", "Ensuring quality and consistency in every post.", "/images/claire.jpg")
        );
    }
}
