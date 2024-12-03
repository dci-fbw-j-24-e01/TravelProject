package dci.j24e01.TravelBlog.controller;

import dci.j24e01.TravelBlog.model.Location;
import dci.j24e01.TravelBlog.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    // Render the form
    @GetMapping("/suggest-location")
    public String showLocationForm(Model model) {
        model.addAttribute("location", new Location());
        return "suggest_location";
    }

    // Handle form submission
    @PostMapping("/suggest-location")
    public String submitLocation(@ModelAttribute Location location) {
        Location saveLocation = locationRepository.save(location);
        if (saveLocation == null) {
            return "redirect:/suggest-location?error";
        }
        return "redirect:/suggest-location?success";
    }
}
